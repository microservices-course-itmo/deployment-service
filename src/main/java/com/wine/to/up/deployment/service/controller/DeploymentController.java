package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
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
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public List<ApplicationInstanceVO> multipleInstancesByApplicationName(
            @PathVariable final String templateName) {
        return deploymentService.getInstancesByAppName(templateName);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.getSingleInstanceById(entityId));
    }

    @GetMapping("/application/get/byName/{name}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final String name) {
        return ResponseEntity.ok(deploymentService.getApplicationByName(name));
    }

    @GetMapping("/application/get/byId/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.getApplicationById(entityId));
    }

    @PostMapping("/application/createOrUpdate")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(@RequestBody final ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.createOrUpdateApplicationTemplate(applicationTemplateVO);
    }

    @DeleteMapping("/application/delete/byName/{name}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<?> deleteApplicationTemplate(@PathVariable final String name) {
        applicationTemplateService.removeEntity(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/applicationInstance/deploy")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody final ApplicationDeployRequest applicationDeployRequest) {
        return deploymentService.deployApplicationInstance(applicationDeployRequest);
    }

    @PostMapping("/applicationInstance/start/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> startApplicationInstance(@PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.startApplication(entityId));
    }

    @PostMapping("/applicationInstance/stop/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> stopApplicationInstance(@PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.stopApplication(entityId));
    }

    @PostMapping("/applicationInstance/restart/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> restartApplicationInstance(@PathVariable final Long entityId) {
        return ResponseEntity.ok(deploymentService.restartApplication(entityId));
    }

    @DeleteMapping("/applicationInstance/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ApplicationInstanceVO deleteApplicationInstance(@PathVariable final Long entityId) {
        return deploymentService.removeApplicationInstanceById(entityId);
    }

    @DeleteMapping("/application/delete/byId/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<?> deleteApplication(@PathVariable final Long entityId) {
        applicationInstanceService.removeEntitiesByIds(Collections.singletonList(entityId));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/application/names")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public List<String> getAllNames() {
        return deploymentService.getAllNames();
    }

    @PostMapping("/settings/set")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public SettingsVO setSettings(@RequestBody final SettingsVO settings) {
        return deploymentService.setSettings(settings);
    }

    @GetMapping("/settings/get")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public SettingsVO getSettings() {
        return deploymentService.getSettings();
    }

    @GetMapping("/applicationInstances/import")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<?> getInstances() {
        applicationImportService.importInstances();
        return ResponseEntity.ok().build();
    }
}

