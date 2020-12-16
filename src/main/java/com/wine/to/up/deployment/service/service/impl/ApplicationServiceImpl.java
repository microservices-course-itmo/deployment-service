package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.service.*;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    private MongoTemplate mongoTemplate;

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
        List<String> names = new ArrayList<>();

        for (String name : mongoTemplate.getCollection("templates").distinct("name", String.class)) {
            names.add(name);
        }
        return names;
    }


    @Override
    public ApplicationTemplateVO createOrUpdateApplication(ApplicationTemplateVO applicationTemplateVO) {
        final boolean updatingEntity = applicationTemplateRepository.countByName(applicationTemplateVO.getName()) > 0;
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getTemplateVersion(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnv(), applicationTemplateVO.getDescription(),
                applicationTemplateVO.getBaseBranch() != null ? applicationTemplateVO.getBaseBranch() : "dev");


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
                .alias("Alias")
                .dateCreated(entity.getDateCreated())
                .description(entity.getDescription())
                .env(entity.getEnvironmentVariables())
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
}
