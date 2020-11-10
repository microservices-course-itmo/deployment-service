package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.LogService;
import com.wine.to.up.deployment.service.service.SequenceGeneratorService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.LogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
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
        ApplicationTemplate applicationTemplate = applicationTemplateRepository
                .findFirstByNameOrderByIdDesc(name)
                .orElseThrow(NotFoundException::new);

        var instances = applicationInstanceService.getInstancesByTemplateName(name);
        var logs = logService.logsByTemplate(applicationTemplate);

        return entityToView(applicationTemplate, instances, logs);

    }

    @Override
    public ApplicationTemplateVO getApplicationTemplate(Long id) {
        ApplicationTemplate applicationTemplate = applicationTemplateRepository
                .findById(id)
                .orElseThrow(NotFoundException::new);

        var instances = applicationInstanceService.getInstancesByTemplateId(applicationTemplate.getId());
        var logs = logService.logsByTemplate(applicationTemplate);

        return entityToView(applicationTemplate, instances, logs);
    }

    @Override
    public ApplicationTemplateVO createOrUpdateApplication(ApplicationTemplateVO applicationTemplateVO) {
        final boolean updatingEntity = applicationTemplateRepository.countByName(applicationTemplateVO.getName()) > 0;
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getTemplateVersion(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnv(), applicationTemplateVO.getDescription());

        var id = sequenceGeneratorService.generateSequence(ApplicationTemplate.SEQUENCE_NAME);
        applicationTemplate.setId(id);

        final LogVO log;
        if (updatingEntity) {
            log = logService.writeLog("system", "Приложение обновлено", applicationTemplateVO.getName(), id);
        } else {
            log = logService.writeLog("system", "Приложение создано", applicationTemplateVO.getName(), id);
        }

        return entityToView(applicationTemplateRepository.save(applicationTemplate), Collections.emptyList(), Collections.singletonList(log));
    }

    public ApplicationTemplateVO entityToView(ApplicationTemplate entity, List<ApplicationInstanceVO> instances, List<LogVO> logs) {
        return ApplicationTemplateVO.builder()
                .alias("Alias")
                .dateCreated(entity.getDateCreated())
                .description(entity.getDescription())
                .env(entity.getEnvironmentVariables())
                .instances(instances)
                .logs(logs)
                .templateVersion(entity.getTemplateVersion())
                .versions(Collections.emptyList())
                .ports(entity.getPortMappings())
                .id(entity.getId())
                .name(entity.getName())
                .createdBy(entity.getCreatedBy())
                .volumes(entity.getVolumes())
                .build();
    }
}
