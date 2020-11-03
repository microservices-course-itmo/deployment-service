package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.Log;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationTemplateRepository applicationTemplateRepository;

    private ApplicationInstanceService applicationInstanceService;

    @Autowired
    public void setApplicationTemplateRepository(ApplicationTemplateRepository applicationTemplateRepository) {
        this.applicationTemplateRepository = applicationTemplateRepository;
    }

    @Autowired
    public void setApplicationInstanceService(ApplicationInstanceService applicationInstanceService) {
        this.applicationInstanceService = applicationInstanceService;
    }

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

        Log log = new Log(new Date(), "STUB");
        List<Log> logs = new ArrayList<>();
        logs.add(log);

        return ApplicationTemplateVO.builder()
                .lastRelease("LATEST")
                .alias("STUB")
                .instances(Collections.emptyList())
                .name("NAME")
                .env(Collections.emptyList())
                .logs(logs)
                .ports(Collections.emptyList())
                .versions(Collections.emptyList())
                .volumes(Collections.emptyList())
                .description("DESCRIPTION")
                .build();
    }

    public ApplicationTemplateVO createApplication(ApplicationTemplateVO applicationTemplateVO) {
        /*ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getLastRelease(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnv());
        applicationTemplateRepository.save(applicationTemplate);*/

        return ApplicationTemplateVO.builder().id(1L).dateCreated(new Date()).build();
    }
}
