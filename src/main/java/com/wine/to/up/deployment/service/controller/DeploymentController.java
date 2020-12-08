package com.wine.to.up.deployment.service.controller;

import com.github.dockerjava.api.model.Service;
import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.List;

@RestController
public class DeploymentController {

    private DeploymentService deploymentService;
    private ApplicationImportService applicationImportService;

    @Autowired
    public void setApplicationImportService(ApplicationImportService applicationImportService) {
        this.applicationImportService = applicationImportService;
    }

    @Autowired
    public void setDeploymentService(final DeploymentService deploymentService) {
        this.deploymentService = deploymentService;
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

    @PostMapping("/applicationInstance/deploy")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody ApplicationDeployRequest applicationDeployRequest) {
        return deploymentService.deployApplicationInstance(applicationDeployRequest);
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

    @GetMapping("/applicationInstances/getInstances")
    public List<String> getInstances() {
        return applicationImportService.importInstances();
    }

}

