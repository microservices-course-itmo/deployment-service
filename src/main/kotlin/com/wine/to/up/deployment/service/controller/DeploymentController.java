package com.wine.to.up.deployment.service.controller;


import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeploymentController {

    private DeploymentService deploymentService;

    @Autowired
    public void setDeploymentService(final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @RequestMapping("/applicationInstances/getInstances/{templateId}")
    public List<ApplicationInstanceVO> multipleInstancesByApplicationId(
            @PathVariable Long templateId) {
        return this.deploymentService.getMultipleInstancesByAppId(templateId);
    }

    @RequestMapping("/applicationInstances/getSingleInstance/{id}")
    public ApplicationInstanceVO singleInstanceByApplicationId(
            @PathVariable long id) {
        return this.deploymentService.getSingleInstanceById(id);
    }

    @RequestMapping("/application/get/{id}")
    public ApplicationTemplateVO getApplication(@PathVariable Long id) {
        return deploymentService.getApplicationById(id);
    }

    @RequestMapping("/application/create")
    public ApplicationTemplateVO createApplicationTemplate(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.createApplicationTemplate(applicationTemplateVO);
    }

    @RequestMapping("/applicationInstance/deploy")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.deployApplicationInstance(applicationTemplateVO);
    }
}

