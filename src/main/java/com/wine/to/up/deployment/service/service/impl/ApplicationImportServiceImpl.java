package com.wine.to.up.deployment.service.service.impl;

import com.github.dockerjava.api.model.PortConfig;
import com.github.dockerjava.api.model.Service;
import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository;
import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationInstance;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.EnvironmentVariable;
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import com.wine.to.up.deployment.service.service.*;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.Attributes;
import com.wine.to.up.deployment.service.vo.Resources;

import javax.ws.rs.NotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ApplicationImportServiceImpl implements ApplicationImportService {

    private final ApplicationTemplateRepository applicationTemplateRepository;
    private final ApplicationInstanceService applicationInstanceService;
    private final SettingsService settingsService;
    private final ApplicationInstanceRepository applicationInstanceRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final ApplicationService applicationService;

    public ApplicationImportServiceImpl(final ApplicationInstanceService applicationInstanceService,
                                        final ApplicationTemplateRepository applicationTemplateRepository,
                                        final SettingsService settingsService,
                                        final ApplicationInstanceRepository applicationInstanceRepository,
                                        final SequenceGeneratorService sequenceGeneratorService,
                                        final ApplicationService applicationService) {
        this.applicationInstanceService = applicationInstanceService;
        this.applicationTemplateRepository = applicationTemplateRepository;
        this.settingsService = settingsService;
        this.applicationInstanceRepository = applicationInstanceRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.applicationService = applicationService;
    }

    @Override
    public void importInstances() {
        final List<Service> instances = getInstances();
        final List<ApplicationTemplateVO> templatesLastVersions = getLastVersionsOfTemplates();
        final Set<String> recreatedTemplatesNames = new HashSet<>();

        for (final Service instance : instances) {
            final String image = instance.getSpec().getTaskTemplate().getContainerSpec().getImage();

            if (isMicroService(image)) {
                final String templateName = getTemplateName(image);
                if (!(isApplicationTemplateExists(templateName))
                        || !(isApplicationInstanceExists(instance.getSpec().getName()))) {
                    createApplicationTemplate(instance, templateName);
                    createApplicationInstance(instance, templateName);
                    recreatedTemplatesNames.add(templateName);
                }
            }
        }
        setLastTemplates(recreatedTemplatesNames, templatesLastVersions);
    }

    private List<Service> getInstances() {
        return applicationInstanceService.getInstances();
    }

    private List<ApplicationTemplateVO> getLastVersionsOfTemplates() {
        return applicationService.getAllNames()
                .stream()
                .map(applicationService::getApplicationTemplate)
                .collect(Collectors.toList());
    }

    private void setLastTemplates(final Set<String> recreatedTemplatesNames, final List<ApplicationTemplateVO> templates) {
        templates.stream()
                .filter(it -> recreatedTemplatesNames.contains(it.getName()))
                .forEach(applicationService::createOrUpdateApplication);
    }

    private boolean isMicroService(final String image) {
        if (image == null)
            return false;
        if (image.indexOf('/') != -1) {
            final String address = image.substring(0, image.indexOf('/'));
            return address.equals(settingsService.getSettings().getImageRegistry());
        }
        return false;
    }

    private boolean isApplicationTemplateExists(final String name) {
        return applicationTemplateRepository.countByName(name) != 0;
    }

    private boolean isApplicationInstanceExists(final String appId) {
        return applicationInstanceRepository.countByAppId(appId) != 0;
    }

    private void createApplicationTemplate(final Service instance, final String templateName) {
        final Map<String, String> ports = getPorts(instance
                .getSpec()
                .getEndpointSpec()
                .getPorts());

        final List<EnvironmentVariable> environmentVariables = getEnvironmentVariables(instance
                .getSpec()
                .getTaskTemplate()
                .getContainerSpec()
                .getEnv());

        final ApplicationTemplateVO applicationTemplateVO = ApplicationTemplateVO.builder()
                .alias("")
                .createdBy("system")
                .description("Imported " + templateName)
                .environmentVariables(environmentVariables)
                .ports(ports)
                .volumes(Collections.emptyList())
                .name(templateName)
                .build();

        applicationService.createOrUpdateApplication(applicationTemplateVO);
    }

    private void createApplicationInstance(final Service instance, final String templateName) {
        final Long id = sequenceGeneratorService.generateSequence(ApplicationInstance.SEQUENCE_NAME);
        final String image = instance.getSpec().getTaskTemplate().getContainerSpec().getImage();

        final ApplicationTemplate template = applicationTemplateRepository
                .findFirstByNameOrderByIdDesc(templateName)
                .orElseThrow(NotFoundException::new);

        final Resources resources;
        final var dockerResources = instance.getSpec().getTaskTemplate().getResources();
        if (dockerResources != null && dockerResources.getLimits() != null && dockerResources.getLimits().getMemoryBytes() != null) { //refactor this shit I'm tired
            resources = new Resources(dockerResources.getLimits().getMemoryBytes());
        } else {
            resources = null;
        }

        final ApplicationInstance applicationInstance = new ApplicationInstance(id,
                templateName, instance.getSpec().getName(),
                template.getTemplateVersion(), getVersion(image),
                System.currentTimeMillis(),
                "system",
                ApplicationInstanceStatus.STARTING,
                "test url",
                templateName,
                new Attributes(false),
                resources
        );


        applicationInstanceRepository.save(applicationInstance);
    }

    private String getVersion(final String image) {
        final String[] split = image.split(":");
        return split[split.length - 1];
    }

    private String getTemplateName(final String instanceName) {
        return instanceName.split("/")[1].split(":")[0];
    }

    private Map<String, String> getPorts(final List<PortConfig> portConfigList) {
        if (portConfigList == null)
            return Collections.emptyMap();
        final Map<String, String> ports = new HashMap<>();
        for (final PortConfig portConfig : portConfigList) {
            ports.put(Integer.toString(portConfig.getPublishedPort()), Integer.toString(portConfig.getTargetPort()));
        }
        return ports;
    }

    private List<EnvironmentVariable> getEnvironmentVariables(final List<String> envVars) {
        if (envVars == null)
            return Collections.emptyList();
        final List<EnvironmentVariable> environmentVariables = new ArrayList<>();
        for (final String envVar : envVars) {
            final int delimiterPosition = envVar.indexOf('=');
            if (delimiterPosition != -1) {
                environmentVariables.add(new EnvironmentVariable(envVar.substring(0, delimiterPosition),
                        envVar.substring(delimiterPosition + 1)));
            }
        }
        return environmentVariables;
    }
}
