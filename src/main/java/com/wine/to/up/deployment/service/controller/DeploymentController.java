package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
public class DeploymentController {

    private DeploymentService deploymentService;
    private ApplicationImportService applicationImportService;

    @Autowired
    public void setApplicationImportService(ApplicationImportService applicationImportService) {
        this.applicationImportService = applicationImportService;
    }

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

    @GetMapping("/applicationInstances/getInstances/byName/{templateName}")
    public ResponseEntity<List<ApplicationInstanceVO>> multipleInstancesByApplicationName(
            @PathVariable String templateName) {
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(this.deploymentService.getInstancesByAppName(templateName));
    }

    @GetMapping("/applicationInstances/getSingleInstance/{id}")
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable Long id) {
        try {
            return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(this.deploymentService.getSingleInstanceById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
        }
    }

    @GetMapping("/application/get/byName/{name}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable String name) {
        try {
            return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.getApplicationByName(name));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
        }
    }

    @GetMapping("/application/get/byId/{id}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.getApplicationById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
        }
    }

    @PostMapping("/application/createOrUpdate")
    public ResponseEntity<ApplicationTemplateVO> createOrUpdateApplicationTemplate(@RequestBody ApplicationTemplateVO applicationTemplateVO) {
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.createOrUpdateApplicationTemplate(applicationTemplateVO));
    }

    @DeleteMapping("/application/delete/byName/{name}")
    public ResponseEntity<String> deleteApplicationTemplate(@PathVariable String name) {
        applicationTemplateService.removeEntity(name);
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
    }

    @PostMapping("/applicationInstance/deploy")
    public ResponseEntity<ApplicationInstanceVO> deployApplicationInstance(@RequestBody ApplicationDeployRequest applicationDeployRequest) {
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.deployApplicationInstance(applicationDeployRequest));
    }

    @DeleteMapping("/application/delete/byId/{id}")
    public ResponseEntity<String> deleteApplicationInstance(@PathVariable Long id) {
        applicationInstanceService.removeEntitiesByIds(Collections.singletonList(id));
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
    }

    @GetMapping("/application/names")
    public ResponseEntity<List<String>> getAllNames() {
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.getAllNames());
    }

    @PostMapping("/settings/set")
    public ResponseEntity<SettingsVO> setSettings(@RequestBody SettingsVO settings) {
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.setSettings(settings));
    }

    @GetMapping("/settings/get")
    public ResponseEntity<SettingsVO> getSettings() {
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").body(deploymentService.getSettings());
    }

    @GetMapping("/applicationInstances/import")
    public ResponseEntity<?> getInstances() {
        applicationImportService.importInstances();
        return ResponseEntity.ok().header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*").build();
    }

}

