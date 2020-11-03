package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ApplicationInstanceService applicationInstanceService;
    private final ApplicationService applicationService;

    @Autowired
    public DeploymentServiceImpl(
            ApplicationInstanceService applicationInstanceService, ApplicationService applicationService
    ) {
        this.applicationInstanceService = applicationInstanceService;
        this.applicationService = applicationService;
    }


    @Override
    public List<ApplicationInstanceVO> getMultipleInstancesByAppId(Long templateId) {
        return applicationInstanceService.getInstancesByTemplateId(templateId);
    }

    @Override
    public ApplicationInstanceVO getSingleInstanceById(long id) {
        return applicationInstanceService.getInstancesByTemplateId(id).stream().findFirst().orElseThrow();
    }

    @Override
    public ApplicationTemplateVO getApplicationById(Long id) {
        return applicationService.getApplicationTemplate(id);
    }

    @Override
    public ApplicationTemplateVO createApplicationTemplate(ApplicationTemplateVO applicationTemplateVO) {
        return applicationService.createApplication(applicationTemplateVO);
    }

    @Override
    public ApplicationInstanceVO deployApplicationInstance(ApplicationTemplateVO applicationTemplateVO) {
        return applicationInstanceService.deployInstance(applicationTemplateVO);
    }
}
