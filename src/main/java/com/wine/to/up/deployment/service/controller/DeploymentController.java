package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.*;
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

@CrossOrigin
@RestController
//@PreAuthorize("isAuthenticated()")
public class    DeploymentController {

    private DeploymentService deploymentService;
    private ApplicationImportService applicationImportService;

    @Autowired
    public void setApplicationImportService(ApplicationImportService applicationImportService) {
        this.applicationImportService = applicationImportService;
    }

    private ApplicationInstanceService applicationInstanceService;

    private ApplicationService applicationTemplateService;

    private ApplicationInstanceManager applicationInstanceManager;

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

    @Autowired
    public void setApplicationInstanceManager(final ApplicationInstanceManager applicationInstanceManager) {
        this.applicationInstanceManager = applicationInstanceManager;
    }

    @GetMapping("/applicationInstances/getInstances/byName/{templateName}")
    public List<ApplicationInstanceVO> multipleInstancesByApplicationName(
            @PathVariable String templateName) {
        return this.deploymentService.getInstancesByAppName(templateName);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{id}")
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.deploymentService.getSingleInstanceById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/application/get/byName/{name}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable String name) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationByName(name));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/application/get/byId/{id}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/application/createOrUpdate")
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.createOrUpdateApplicationTemplate(applicationTemplateVO);
    }

    @DeleteMapping("/application/delete/byName/{name}")
    public void deleteApplicationTemplate(@PathVariable String name) {
        applicationTemplateService.removeEntity(name);
    }

    @PostMapping("/applicationInstance/deploy")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody ApplicationDeployRequest applicationDeployRequest) {
        return deploymentService.deployApplicationInstance(applicationDeployRequest);
    }

    @PostMapping("/applicationInstance/start/{id}")
    public void startApplicationInstance(@PathVariable Long id)   {
        applicationInstanceManager.startApplication(this.deploymentService.getSingleInstanceById(id));
    }

    @PostMapping("/applicationInstance/stop/{id}")
    public void stopApplicationInstance(@PathVariable Long id)   {
        applicationInstanceManager.stopApplication(this.deploymentService.getSingleInstanceById(id));
    }

    @PostMapping("/applicationInstance/restart/{id}")
    public void restartApplicationInstance(@PathVariable Long id)   {
        applicationInstanceManager.restartApplication(this.deploymentService.getSingleInstanceById(id));
    }

    @DeleteMapping("/applicationInstance/{id}")
    public ApplicationInstanceVO deleteApplicationInstance(@PathVariable final Long id) {
        return deploymentService.removeApplicationInstanceById(id);
    }

    @DeleteMapping("/application/delete/byId/{id}")
    public void deleteApplication(@PathVariable final Long id) {
        applicationInstanceService.removeEntitiesByIds(Collections.singletonList(id));
    }

    @GetMapping("/application/names")
    public List<String> getAllNames() {
        return deploymentService.getAllNames();
    }

    @PostMapping("/settings/set")
    public SettingsVO setSettings(@RequestBody SettingsVO settings) {
        return deploymentService.setSettings(settings);
    }

    @GetMapping("/settings/get")
    public SettingsVO getSettings() {
        return deploymentService.getSettings();
    }

    @GetMapping("/applicationInstances/import")
    public ResponseEntity<?> getInstances() {
        applicationImportService.importInstances();
        return ResponseEntity.ok().build();
    }

}

