package com.wine.to.up.deployment.service.service.impl;

import com.github.dockerjava.api.model.PortConfig;
import com.github.dockerjava.api.model.Service;
import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository;
import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationInstance;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.Environment;
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import com.wine.to.up.deployment.service.service.*;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;

import javax.ws.rs.NotFoundException;
import java.util.*;

@org.springframework.stereotype.Service
public class ApplicationImportServiceImpl implements ApplicationImportService {
    private final ApplicationTemplateRepository applicationTemplateRepository;
    private final ApplicationInstanceService applicationInstanceService;
    private final SettingsService settingsService;
    private final ApplicationInstanceRepository applicationInstanceRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final ApplicationService applicationService;

    public ApplicationImportServiceImpl(ApplicationInstanceService applicationInstanceService,
                                        ApplicationTemplateRepository applicationTemplateRepository,
                                        SettingsService settingsService,
                                        ApplicationInstanceRepository applicationInstanceRepository,
                                        SequenceGeneratorService sequenceGeneratorService,
                                        ApplicationService applicationService) {
        this.applicationInstanceService = applicationInstanceService;
        this.applicationTemplateRepository = applicationTemplateRepository;
        this.settingsService = settingsService;
        this.applicationInstanceRepository = applicationInstanceRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
        this.applicationService = applicationService;
    }

    private List<Service> getInstances() {
        return applicationInstanceService.getInstances();
    }

    @Override
    public List<String> importInstances() {
        List<Service> instances = getInstances();
        List<String> list = new ArrayList<>();
        for (Service instance : instances) {
            String templateName = getTemplateName(instance.getSpec().getName());

            String image = instance.getSpec().getTaskTemplate().getContainerSpec().getImage();

            if (isMicroService(image)) {
                if (!(isApplicationTemplateExists(templateName))) {
                    createApplicationTemplate(instance, templateName);
                    createApplicationInstance(instance, templateName);
                    list.add(templateName + " New Template");
                }
                else {
                    if (!(isApplicationInstanceExists(instance.getSpec().getName()))) {
                        createApplicationInstance(instance, templateName);
                        list.add(templateName + " New Instance");
                    }
                }
            }
        }
        return list;
    }

    private boolean isMicroService(String image) {
        if (image == null)
            return false;
        if (image.indexOf('/') != -1) {
            String address = image.substring(0, image.indexOf('/'));
            return address.equals("registry:5000")
                    || address.equals("0.0.0.0:25001")
                    || address.equals(settingsService.getSettings().getImageRegistry());
        }
        return false;
    }

    private boolean isApplicationTemplateExists(String name) {
        return applicationTemplateRepository.countByName(name) != 0;
    }

    private boolean isApplicationInstanceExists(String appId) {
        return applicationInstanceRepository.countByAppId(appId) != 0;
    }

    private void createApplicationTemplate(Service instance, String templateName) {
        Map<String, String> ports = getPorts(instance
                .getSpec()
                .getEndpointSpec()
                .getPorts());

        List<Environment> environmentVariables = getEnvironmentVariables(instance
                .getSpec()
                .getTaskTemplate()
                .getContainerSpec()
                .getEnv());

        ApplicationTemplateVO applicationTemplateVO = ApplicationTemplateVO.builder()
                .alias("")
                .createdBy("system")
                .description("Imported " + templateName)
                .env(environmentVariables)
                .ports(ports)
                .volumes(Collections.emptyList())
                .name(templateName)
                .build();

        applicationService.createOrUpdateApplication(applicationTemplateVO);
    }

    private void createApplicationInstance(Service instance, String templateName) {
        Long id = sequenceGeneratorService.generateSequence(ApplicationInstance.SEQUENCE_NAME);
        String image = instance.getSpec().getTaskTemplate().getContainerSpec().getImage();

        ApplicationTemplate template = applicationTemplateRepository
                .findFirstByNameOrderByIdDesc(templateName)
                .orElseThrow(NotFoundException::new);

        ApplicationInstance applicationInstance = new ApplicationInstance(id,
                templateName, instance.getSpec().getName(),
                template.getTemplateVersion(), getVersion(image),
                System.currentTimeMillis(),
                "system",
                ApplicationInstanceStatus.STARTING,
                "test url",
                "");


        applicationInstanceRepository.save(applicationInstance);
    }

    private String getVersion(String image) {
        String[] split = image.split(":");
        return split[split.length - 1];
    }

    private String getTemplateName(String instanceName) {
        if (instanceName.indexOf('_') != -1) {
            return instanceName.substring(0, instanceName.indexOf('_'));
        }
        return instanceName;
    }
    private Map<String, String> getPorts(List<PortConfig> portConfigList) {
        if (portConfigList == null)
            return Collections.emptyMap();
        Map<String, String> ports = new HashMap<>();
        for (PortConfig portConfig : portConfigList) {
            ports.put(Integer.toString(portConfig.getPublishedPort()), Integer.toString(portConfig.getTargetPort()));
        }
        return ports;
    }

    private List<Environment> getEnvironmentVariables(List<String> envVars) {
        if (envVars == null)
            return Collections.emptyList();
        List<Environment> environmentVariables = new ArrayList<>();
        for (String envVar : envVars) {
            int delimiterPosition = envVar.indexOf('=');
            environmentVariables.add(new Environment(envVar.substring(0, delimiterPosition),
                    envVar.substring(delimiterPosition + 1)));
        }
        return environmentVariables;
    }
}
