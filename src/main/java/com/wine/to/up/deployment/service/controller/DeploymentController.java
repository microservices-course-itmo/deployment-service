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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.ServiceUnavailableException;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
@ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
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
        return this.deploymentService.getInstancesByAppName(templateName);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable final Long entityId) {
        try {
            return ResponseEntity.ok(this.deploymentService.getSingleInstanceById(entityId));
        } catch (final NotFoundException e) {
            return constructErrorResponse(e);
        }
    }

    @GetMapping("/application/get/byName/{name}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final String name) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationByName(name));
        } catch (final NotFoundException e) {
            return constructErrorResponse(e);
        }
    }

    @GetMapping("/application/get/byId/{entityId}")
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final Long entityId) {
        try {
            return ResponseEntity.ok(deploymentService.getApplicationById(entityId));
        } catch (final NotFoundException e) {
            return constructErrorResponse(e);
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

    @PostMapping("/applicationInstance/start/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> startApplicationInstance(@PathVariable Long entityId) {
        try {
            return ResponseEntity.ok(deploymentService.startApplication(entityId));
        } catch (final Throwable ex) {
            return constructErrorResponse(ex);
        }
    }

    @PostMapping("/applicationInstance/stop/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> stopApplicationInstance(@PathVariable Long entityId) {
        try {
            return ResponseEntity.ok(deploymentService.stopApplication(entityId));
        } catch (final Throwable ex) {
            return constructErrorResponse(ex);
        }
    }

    @PostMapping("/applicationInstance/restart/{entityId}")
    public ResponseEntity<ApplicationInstanceVO> restartApplicationInstance(@PathVariable Long entityId) {
        try {
            return ResponseEntity.ok(deploymentService.restartApplication(entityId));
        } catch (final Throwable ex) {
            return constructErrorResponse(ex);
        }
    }
  
    @DeleteMapping("/applicationInstance/{entityId}")
    public ApplicationInstanceVO deleteApplicationInstance(@PathVariable final Long entityId) {
        return deploymentService.removeApplicationInstanceById(entityId);
    }

    @DeleteMapping("/application/delete/byId/{entityId}")
    public void deleteApplication(@PathVariable final Long entityId) {
        applicationInstanceService.removeEntitiesByIds(Collections.singletonList(entityId));
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

    private ResponseEntity constructErrorResponse(Throwable e) {
        if (e instanceof com.github.dockerjava.api.exception.NotFoundException || e instanceof NotFoundException) {
            return ResponseEntity.notFound().build();
        } else if (e instanceof ServiceUnavailableException) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

