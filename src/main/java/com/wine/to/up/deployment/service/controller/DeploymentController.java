package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.service.vo.ApplicationInstance;
import com.wine.to.up.deployment.service.service.vo.DiscoveryClient;
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

    private DiscoveryClient discoveryClient;

    @RequestMapping("/application-instances/{applicationName}")
    public List<ApplicationInstance> appInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
}

