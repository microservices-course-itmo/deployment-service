package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
//@PreAuthorize("isAuthenticated()")
public class DeploymentController {

    private DeploymentService deploymentService;

    private ApplicationImportService applicationImportService;

    private ApplicationInstanceService applicationInstanceService;

    private ApplicationService applicationTemplateService;

    @Autowired
    public void setApplicationImportService(final ApplicationImportService applicationImportService) {
        this.applicationImportService = applicationImportService;
    }

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

    @GetMapping("/applicationInstances/getInstances/byName/{templateName}")
    public List<ApplicationInstanceVO> multipleInstancesByApplicationName(
            @PathVariable final String templateName) {
        return deploymentService.getInstancesByAppName(templateName);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.getSingleInstanceById(entityId));
    }

    @GetMapping("/application/get/byName/{name}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final String name) {
        return ResponseEntity.ok(deploymentService.getApplicationByName(name));
    }

    @GetMapping("/application/get/byId/{entityId}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.getApplicationById(entityId));
    }

    @PostMapping("/application/createOrUpdate")
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(@RequestBody final ApplicationTemplateVO applicationTemplateVO) {
        log.info("Create or Update method called in DeploymentController class");
        return deploymentService.createOrUpdateApplicationTemplate(applicationTemplateVO);
    }

    @DeleteMapping("/application/delete/byName/{name}")
    public ResponseEntity<?> deleteApplicationTemplate(@PathVariable final String name) {
        applicationTemplateService.removeEntity(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/applicationInstance/deploy")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody final ApplicationDeployRequest applicationDeployRequest) {
        return deploymentService.deployApplicationInstance(applicationDeployRequest);
    }

    @PostMapping("/applicationInstance/start/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> startApplicationInstance(@PathVariable Long entityId) {
        return ResponseEntity.ok(deploymentService.startApplication(entityId));
    }

    @PostMapping("/applicationInstance/stop/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> stopApplicationInstance(@PathVariable Long entityId) {
        return ResponseEntity.ok(deploymentService.stopApplication(entityId));
    }

    @PostMapping("/applicationInstance/restart/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> restartApplicationInstance(@PathVariable Long entityId) {
        return ResponseEntity.ok(deploymentService.restartApplication(entityId));
    }

    @DeleteMapping("/applicationInstance/{entityId}")
    public ApplicationInstanceVO deleteApplicationInstance(@PathVariable final Long entityId) {
        return deploymentService.removeApplicationInstanceById(entityId);
    }

    @DeleteMapping("/application/delete/byId/{entityId}")
    public ResponseEntity<?> deleteApplication(@PathVariable final Long entityId) {
        applicationInstanceService.removeEntitiesByIds(Collections.singletonList(entityId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/application/names")
    public List<String> getAllNames() {
        return deploymentService.getAllNames();
    }

    @PostMapping("/settings/set")
    public SettingsVO setSettings(@RequestBody final SettingsVO settings) {
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

