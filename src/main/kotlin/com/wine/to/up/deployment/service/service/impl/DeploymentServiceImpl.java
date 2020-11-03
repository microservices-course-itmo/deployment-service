package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ApplicationInstanceService applicationInstanceService;

    @Autowired
    public DeploymentServiceImpl(
            ApplicationInstanceService applicationInstanceService
    ) {
        this.applicationInstanceService = applicationInstanceService;
    }


    @Override
    public List<ApplicationInstanceVO> getMultipleInstancesByAppId(Long templateId) {
        return applicationInstanceService.getInstancesByTemplateId(templateId);
    }

    @Override
    public ApplicationInstanceVO getSingleInstanceById(long id) {
        return applicationInstanceService.getInstancesByTemplateId(id).stream().findFirst().orElseThrow();
    }
}
