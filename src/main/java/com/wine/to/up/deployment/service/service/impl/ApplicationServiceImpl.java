package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.Log;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.LogService;
import com.wine.to.up.deployment.service.service.SequenceGeneratorService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationTemplateRepository applicationTemplateRepository;

    private ApplicationInstanceService applicationInstanceService;

    private SequenceGeneratorService sequenceGeneratorService;

    private LogService logService;

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
        ApplicationTemplate applicationTemplate = applicationTemplateRepository.findFirstByNameOrderByIdDesc(name).orElseThrow();

        var instances = applicationInstanceService.getInstancesByTemplateId(applicationTemplate.getId());
        var logs = logService.logsByInstances(instances);

        return entityToView(applicationTemplate, instances, logs);

    }

    @Override
    public ApplicationTemplateVO createApplication(ApplicationTemplateVO applicationTemplateVO) {
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getVersions(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnv(), applicationTemplateVO.getDescription());
        //applicationTemplateRepository.save(applicationTemplate);

        var id = sequenceGeneratorService.generateSequence(ApplicationTemplate.SEQUENCE_NAME);
        applicationTemplate.setId(id);

        return entityToView(applicationTemplateRepository.save(applicationTemplate), Collections.emptyList(), Collections.emptyList());
    }

    public ApplicationTemplateVO entityToView(ApplicationTemplate entity, List<ApplicationInstanceVO> instances, List<Log> logs) {
        return ApplicationTemplateVO.builder()
                .alias("Alias")
                .dateCreated(entity.getDateCreated())
                .description(entity.getDescription())
                .env(entity.getEnvironmentVariables())
                .instances(instances)
                .logs(logs)
                .lastRelease("RELEASE")
                .versions(entity.getTemplateVersions())
                .ports(entity.getPortMappings())
                .id(entity.getId())
                .name(entity.getName())
                .createdBy(entity.getCreatedBy())
                .volumes(entity.getVolumes())
                .build();
    }
}
