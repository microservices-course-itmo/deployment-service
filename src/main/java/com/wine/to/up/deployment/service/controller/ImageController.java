package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.dto.ImageDto;
import com.wine.to.up.deployment.service.enums.ImageRequestType;
import com.wine.to.up.deployment.service.service.impl.DeploymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping(value = "/images")
    public ImageDto images() {
        return deploymentService.processImageRequest(ImageDto.builder().imageRequestType(ImageRequestType.FIND).build());
    }
}
