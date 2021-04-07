package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.ApplicationInstanceManager;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.SettingsService;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationDeployRequestWrapper;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("isAuthenticated()")
@ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
public class DeploymentController {

    private ApplicationImportService applicationImportService;

    private ApplicationInstanceService applicationInstanceService;

    private ApplicationService applicationTemplateService;

    private ApplicationInstanceManager applicationInstanceManager;

    private SettingsService settingsService;

    @Autowired
    public void setApplicationImportService(final ApplicationImportService applicationImportService) {
        this.applicationImportService = applicationImportService;
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
        return this.applicationInstanceService.getInstancesByTemplateName(templateName);
    }

    @GetMapping("/applicationInstances/getSingleInstance/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> singleInstanceByApplicationId(
            @PathVariable final Long entityId) {
        return ResponseEntity.ok(applicationInstanceService.getInstanceById(entityId));
    }

    @GetMapping("/application/get/byName/{name}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final String name) {
        return ResponseEntity.ok(applicationTemplateService.getApplicationTemplate(name));
    }

    @GetMapping("/application/get/byId/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationTemplateVO> getApplication(@PathVariable final Long entityId) {
        return ResponseEntity.ok(applicationTemplateService.getApplicationTemplate(entityId));
    }

    @PostMapping("/application/createOrUpdate")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(@RequestBody final ApplicationTemplateVO applicationTemplateVO) {
        return applicationTemplateService.createOrUpdateApplication(applicationTemplateVO);
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
        var actualVo = applicationTemplateService.getApplicationTemplate(applicationDeployRequest.getName());
        ApplicationDeployRequestWrapper applicationDeployRequestWrapper = new ApplicationDeployRequestWrapper(
                applicationDeployRequest.getVersion(),
                actualVo,
                applicationDeployRequest.getAlias(),
                applicationDeployRequest.getAttributes(),
                applicationDeployRequest.getResources());
        return applicationInstanceService.deployInstance(applicationDeployRequestWrapper);
    }

    @PostMapping("/applicationInstance/start/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> startApplicationInstance(@PathVariable Long entityId) {
        return ResponseEntity.ok(applicationInstanceManager.startApplication(applicationInstanceService.getInstanceById(entityId)));
    }

    @PostMapping("/applicationInstance/stop/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> stopApplicationInstance(@PathVariable Long entityId) {
        return ResponseEntity.ok(applicationInstanceManager.stopApplication(applicationInstanceService.getInstanceById(entityId)));
    }

    @PostMapping("/applicationInstance/restart/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<ApplicationInstanceVO> restartApplicationInstance(@PathVariable Long entityId) {
        return ResponseEntity.ok(applicationInstanceManager.restartApplication(applicationInstanceService.getInstanceById(entityId)));
    }

    @DeleteMapping("/applicationInstance/{entityId}")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ApplicationInstanceVO deleteApplicationInstance(@PathVariable final Long entityId) {
        return applicationInstanceService.removeEntityById(entityId);
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
        return new ArrayList<>(applicationTemplateService.getAllNames().stream().filter(Objects::nonNull).collect(Collectors.toSet()));
    }

    @PostMapping("/settings/set")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public SettingsVO setSettings(@RequestBody final SettingsVO settings) {
        return settingsService.setSettings(settings);
    }

    @GetMapping("/settings/get")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public SettingsVO getSettings() {
        return settingsService.getSettings();
    }

    @GetMapping("/applicationInstances/import")
    @ApiOperation(value = "Main controller", authorizations = {@Authorization(value = "jwtToken")})
    public ResponseEntity<?> getInstances() {
        applicationImportService.importInstances();
        return ResponseEntity.ok().build();
    }
}

