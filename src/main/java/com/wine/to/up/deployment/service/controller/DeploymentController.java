package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeploymentController {

    private DeploymentService deploymentService;

    @Autowired
    public void setDeploymentService(final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }
}
