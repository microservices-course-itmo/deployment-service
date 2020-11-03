package com.wine.to.up.deployment.service.controller;


import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeploymentController {

    private DeploymentService deploymentService;

    @Autowired
    public void setDeploymentService(final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @GetMapping("/applicationInstances/getInstances/{templateId}")
    public List<ApplicationInstanceVO> multipleInstancesByApplicationId(
            @PathVariable Long templateId) {
        return this.deploymentService.getMultipleInstancesByAppId(templateId);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{id}")
    public ApplicationInstanceVO singleInstanceByApplicationId(
            @PathVariable long id) {
        return this.deploymentService.getSingleInstanceById(id);
    }

    @GetMapping("/application/get/{id}")
    public ApplicationTemplateVO getApplication(@PathVariable Long id) {
        return deploymentService.getApplicationById(id);
    }

    @PostMapping("/application/create")
    public ApplicationTemplateVO createApplicationTemplate(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.createApplicationTemplate(applicationTemplateVO);
    }

    @PostMapping("/applicationInstance/deploy")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.deployApplicationInstance(applicationTemplateVO);
    }
}

