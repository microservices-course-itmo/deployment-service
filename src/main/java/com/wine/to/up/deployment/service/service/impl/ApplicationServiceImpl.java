package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.commonlib.security.AuthenticationProvider;
import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.EnvironmentVariable;
import com.wine.to.up.deployment.service.enums.StandardEnvironmentVariable;
import com.wine.to.up.deployment.service.service.*;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.LogVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationTemplateRepository applicationTemplateRepository;

    private ApplicationInstanceService applicationInstanceService;

    private SequenceGeneratorService sequenceGeneratorService;

    private LogService logService;

    private ServiceVersionProvider serviceVersionProvider;

    @Autowired
    public void setServiceVersionProvider(final ServiceVersionProvider serviceVersionProvider) {
        this.serviceVersionProvider = serviceVersionProvider;
    }

    @Autowired
    public void setLogService(final LogService logService) {
        this.logService = logService;
    }

    @Autowired
    public void setSequenceGeneratorService(final SequenceGeneratorService sequenceGeneratorService) {
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @Autowired
    public void setApplicationTemplateRepository(ApplicationTemplateRepository applicationTemplateRepository) {
        this.applicationTemplateRepository = applicationTemplateRepository;
    }

    @Autowired
    public void setApplicationInstanceService(ApplicationInstanceService applicationInstanceService) {
        this.applicationInstanceService = applicationInstanceService;
    }

    @Override
    public ApplicationTemplateVO getApplicationTemplate(String name) {
        ApplicationTemplate applicationTemplate = applicationTemplateRepository
                .findFirstByNameOrderByIdDesc(name)
                .orElseThrow(NotFoundException::new);
        var versions = serviceVersionProvider.findAllVersions(applicationTemplate);

        var instances = applicationInstanceService.getInstancesByTemplateName(name);
        //TODO limit should be applied automatically
        var logs = logService.logsByTemplate(applicationTemplate, 30);

        return entityToView(applicationTemplate, instances, logs, versions);

    }

    @Override
    public ApplicationTemplateVO getApplicationTemplate(Long id) {
        ApplicationTemplate applicationTemplate = applicationTemplateRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);

        var versions = serviceVersionProvider.findAllVersions(applicationTemplate);
        var instances = applicationInstanceService.getInstancesByTemplateId(applicationTemplate.getId());
        //TODO limit should be applied automatically
        var logs = logService.logsByTemplate(applicationTemplate, 30);

        return entityToView(applicationTemplate, instances, logs, versions);
    }

    @Override
    public List<String> getAllNames() {
        return applicationTemplateRepository.findAll()
                .stream()
                .map(ApplicationTemplate::getName)
                .collect(Collectors.toList());
    }

    @Override
    public void removeEntity(String name) {
        final var entities = applicationTemplateRepository.findAllByName(name);

        if (entities.isEmpty()) {
            throw new NotFoundException();
        }

        final var instances = applicationInstanceService.getInstancesByTemplateName(name);
        applicationInstanceService.removeEntities(
                applicationInstanceService.viewsToEntities(instances)
        );
        entities.forEach((it) -> {
            applicationTemplateRepository.deleteById(it.getId());
        });
    }


    @Override
    public ApplicationTemplateVO createOrUpdateApplication(ApplicationTemplateVO applicationTemplateVO) {
        if(applicationTemplateVO.getName().equals("")) {
            throw new BadRequestException("Name cannot be null");
        }
        final boolean updatingEntity = applicationTemplateRepository.countByName(applicationTemplateVO.getName()) > 0;

        populateStandardEnvVars(applicationTemplateVO);

        ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getTemplateVersion(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnvironmentVariables(),
                applicationTemplateVO.getDescription(),
                applicationTemplateVO.getBaseBranch() != null ? applicationTemplateVO.getBaseBranch() : "dev");

        if (StringUtils.isEmpty(applicationTemplate.getCreatedBy())) {
            applicationTemplate.setCreatedBy(AuthenticationProvider.getUser().getId().toString());
        }

        var id = sequenceGeneratorService.generateSequence(ApplicationTemplate.SEQUENCE_NAME);
        var versions = serviceVersionProvider.findAllVersions(applicationTemplate);
        applicationTemplate.setId(id);

        final LogVO log;
        if (updatingEntity) {
            log = logService.writeLog("system", "Приложение обновлено", applicationTemplateVO.getName(), id);
        } else {
            log = logService.writeLog("system", "Приложение создано", applicationTemplateVO.getName(), id);
        }

        applicationTemplate.setTemplateVersion(sequenceGeneratorService.generateSequence("applicationTemplate_" + applicationTemplate.getName()));
        return entityToView(applicationTemplateRepository.save(applicationTemplate), Collections.emptyList(), Collections.singletonList(log), versions);
    }

    public ApplicationTemplateVO entityToView(ApplicationTemplate entity, List<ApplicationInstanceVO> instances, List<LogVO> logs, List<String> versions) {
        return ApplicationTemplateVO.builder()
                .dateCreated(entity.getDateCreated())
                .description(entity.getDescription())
                .environmentVariables(entity.getEnvironmentVariables())
                .instances(instances)
                .logs(logs)
                .templateVersion(entity.getTemplateVersion())
                .versions(versions)
                .ports(entity.getPortMappings())
                .id(entity.getId())
                .name(entity.getName())
                .createdBy(entity.getCreatedBy())
                .volumes(entity.getVolumes())
                .baseBranch(entity.getBaseBranch())
                .build();
    }

    private void populateStandardEnvVars(ApplicationTemplateVO applicationTemplateVO) {
        List<String> varNamesInTemplate = applicationTemplateVO.getEnvironmentVariables().stream()
                .map(EnvironmentVariable::getName).collect(Collectors.toList());

        for (StandardEnvironmentVariable standardVar : StandardEnvironmentVariable.values()) {
            if (!varNamesInTemplate.contains(standardVar.getEnvironmentVariable().getName())) {
                applicationTemplateVO.addEnvironmentVariable(standardVar.getEnvironmentVariable());
            }
        }
    }

}
