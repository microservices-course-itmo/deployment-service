package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.impl.DeploymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author akosturenko
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {

    public final DeploymentServiceImpl deploymentService;


    @Autowired
    public ImageController(final DeploymentServiceImpl deploymentService) {
        this.deploymentService = deploymentService;
    }

    @PostMapping(value = "/api/image/{id}")
    public String startService(@PathVariable String id) {
        return id;
    }
}
