package com.wine.to.up.deployment.service.controller;


import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/applicationInstances/getInstances/{id}")
    public List<ApplicationInstanceVO> multipleInstancesByApplicationId(
            @PathVariable String id) {
        return this.deploymentService.getMultipleInstancesByAppId(id);
    }

    @RequestMapping("/applicationInstances/getSingleInstance/{id}")
    public ApplicationInstanceVO singleInstanceByApplicationId(
            @PathVariable long id) {
        return this.deploymentService.getSingleInstanceById(id);
    }

}

