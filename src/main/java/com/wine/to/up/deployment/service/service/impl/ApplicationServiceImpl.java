package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationInstance;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.Log;
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.SequenceGeneratorService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationTemplateRepository applicationTemplateRepository;

    private ApplicationInstanceService applicationInstanceService;

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public void setApplicationTemplateRepository(ApplicationTemplateRepository applicationTemplateRepository) {
        this.applicationTemplateRepository = applicationTemplateRepository;
    }

    @Autowired
    public void setApplicationInstanceService(ApplicationInstanceService applicationInstanceService) {
        this.applicationInstanceService = applicationInstanceService;
    }

    //TODO rewrite with connection to database
    @Override
    public ApplicationTemplateVO getApplicationTemplate(Long id) {
        /*List<ApplicationInstanceVO> instancesByTemplateId = applicationInstanceService.getInstancesByTemplateId(id);
        ApplicationTemplate applicationTemplate = applicationTemplateRepository.findById(id);

        return ApplicationTemplateVO.builder()
                .lastRelease(applicationTemplate.getTemplateVersion())
                .alias("STUB")
                .instances(instancesByTemplateId)
                .name(applicationTemplate.getName())
                .env(applicationTemplate.getEnvironmentVariables())
                .logs(logs)
                .ports(applicationTemplate.getPortMappings())
                .versions(Collections.emptyList())
                .volumes(applicationTemplate.getVolumes())
                .description("STUB")
                .build();*/

        return ApplicationTemplateVO.builder()
                .lastRelease("LATEST")
                .alias("alias")
                .instances(Collections.singletonList(ApplicationInstanceVO.builder()
                        .id(1L)
                        .version("1.0.1")
                        .createdBy("asukhoa")
                        .alias("alias")
                        .status(ApplicationInstanceStatus.RUNNING)
                        .build()))
                .name("NAME")
                .env(Collections.emptyList())
                .logs(Collections.singletonList(new Log(new Date(), "log message")))
                .ports(Collections.emptyList())
                .versions(Collections.emptyList())
                .volumes(Collections.emptyList())
                .description("DESCRIPTION")
                .build();
    }

    //TODO rewrite with connection to database
    @Override
    public ApplicationTemplateVO createApplication(ApplicationTemplateVO applicationTemplateVO) {
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getVersions(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnv(), applicationTemplateVO.getDescription());
        applicationTemplateRepository.save(applicationTemplate);

        var id = sequenceGeneratorService.generateSequence(ApplicationTemplate.SEQUENCE_NAME);
        applicationTemplate.setId(id);

        return entityToView(applicationTemplateRepository.save(applicationTemplate), Collections.emptyList(), Collections.emptyList());
    }

    public ApplicationTemplateVO entityToView(ApplicationTemplate entity, List<ApplicationInstance> instances, List<Log> logs) {
        return ApplicationTemplateVO.builder()
                .alias("Alias")
                .dateCreated(entity.getDateCreated())
                .description(entity.getDescription())
                .env(entity.getEnvironmentVariables())
                .instances(applicationInstanceService.entitiesToVies(instances))
                .logs(logs)
                .lastRelease("RELEASE")
                .versions(entity.getTemplateVersions())
                .id(entity.getId())
                .name(entity.getName())
                .createdBy(entity.getCreatedBy())
                .volumes(entity.getVolumes())
                .build();
    }
}
