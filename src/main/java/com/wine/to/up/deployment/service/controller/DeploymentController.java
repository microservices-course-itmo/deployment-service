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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
public class DeploymentController {

    private DeploymentService deploymentService;
    private ApplicationImportService applicationImportService;

    @Autowired
    public void setApplicationImportService(final ApplicationImportService applicationImportService) {
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
    public List<ApplicationInstanceVO> multipleInstancesByApplicationName(
            @PathVariable final String templateName) {
        return this.deploymentService.getInstancesByAppName(templateName);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{id}")
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable final Long id) {
        try {
            return ResponseEntity.ok(this.deploymentService.getSingleInstanceById(id));
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/application/get/byName/{name}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final String name) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationByName(name));
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/application/get/byId/{id}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final Long id) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationById(id));
        } catch (final NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/application/createOrUpdate")
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(@RequestBody final ApplicationTemplateVO applicationTemplateVO) {
        return deploymentService.createOrUpdateApplicationTemplate(applicationTemplateVO);
    }

    @DeleteMapping("/application/delete/byName/{name}")
    public void deleteApplicationTemplate(@PathVariable final String name) {
        applicationTemplateService.removeEntity(name);
    }

    @PostMapping("/applicationInstance/deploy")
    public ApplicationInstanceVO deployApplicationInstance(@RequestBody final ApplicationDeployRequest applicationDeployRequest) {
        return deploymentService.deployApplicationInstance(applicationDeployRequest);
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

