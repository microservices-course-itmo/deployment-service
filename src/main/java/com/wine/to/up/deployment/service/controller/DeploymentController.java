package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
public class DeploymentController {

    private DeploymentService deploymentService;

    private ApplicationInstanceService applicationInstanceService;

    private ApplicationService applicationTemplateService;

    @Autowired
    public void setDeploymentService(final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
    }

    @Autowired
    public void setApplicationInstanceService(final ApplicationInstanceService applicationInstanceService) {
        this.applicationInstanceService = applicationInstanceService;
    }

    @Autowired
    public void setApplicationTemplateService(final ApplicationService applicationTemplateService) {
        this.applicationTemplateService = applicationTemplateService;
    }

    @GetMapping("/applicationInstances/name/{templateName}")
    public List<ApplicationInstanceVO> multipleInstancesByApplicationName(
            @PathVariable String templateName) {
        return this.deploymentService.getInstancesByAppName(templateName);
    }

    @GetMapping("/applicationInstances/{id}")
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.deploymentService.getSingleInstanceById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/application/name/{name}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable String name) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationByName(name));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/application/{id}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/application")
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.createOrUpdateApplicationTemplate(applicationTemplateVO);
    }

    @DeleteMapping("/application/name/{name}")
    public void deleteApplicationTemplate(@PathVariable String name) {
        applicationTemplateService.removeEntity(name);
    }

    @PostMapping("/applicationInstance")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody ApplicationDeployRequest applicationDeployRequest) {
        return deploymentService.deployApplicationInstance(applicationDeployRequest);
    }

    @DeleteMapping("/application/{id}")
    public void deleteApplicationInstance(@PathVariable Long id) {
        applicationInstanceService.removeEntitiesByIds(Collections.singletonList(id));
    }

    @GetMapping("/application")
    public List<String> getAllNames() {
        return deploymentService.getAllNames();
    }

    @PostMapping("/settings")
    public SettingsVO setSettings(@RequestBody SettingsVO settings) {
        return deploymentService.setSettings(settings);
    }

    @GetMapping("/settings")
    public SettingsVO getSettings() {
        return deploymentService.getSettings();
    }

}

