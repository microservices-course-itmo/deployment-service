package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.service.*;
import com.wine.to.up.deployment.service.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ApplicationInstanceService applicationInstanceService;
    private final ApplicationService applicationService;
    private final SettingsService settingsService;
    private final ApplicationInstanceManager applicationInstanceManager;

    @Autowired
    public DeploymentServiceImpl(
            ApplicationInstanceService applicationInstanceService, ApplicationService applicationService,
            final SettingsService settingsService, ApplicationInstanceManager applicationInstanceManager) {
        this.applicationInstanceService = applicationInstanceService;
        this.applicationService = applicationService;
        this.settingsService = settingsService;
        this.applicationInstanceManager = applicationInstanceManager;
    }

    @Override
    public List<String> getAllNames() {
        return new ArrayList<>(applicationService.getAllNames().stream().filter(Objects::nonNull).collect(Collectors.toSet()));
    }

    @Override
    public List<ApplicationInstanceVO> getInstancesByAppName(String templateName) {
        return applicationInstanceService.getInstancesByTemplateName(templateName);
    }

    @Override
    public ApplicationInstanceVO getSingleInstanceById(Long id) {

        return applicationInstanceService.getInstanceById(id);
    }

    @Override
    public ApplicationTemplateVO getApplicationByName(String name) {
        return applicationService.getApplicationTemplate(name);
    }

    @Override
    public ApplicationInstanceVO removeApplicationInstanceById(Long id) {
        return applicationInstanceService.removeEntityById(id);
    }

    @Override
    public ApplicationTemplateVO getApplicationById(Long id) {
        return applicationService.getApplicationTemplate(id);
    }

    @Override
    public ApplicationTemplateVO createOrUpdateApplicationTemplate(ApplicationTemplateVO applicationTemplateVO) {
        return applicationService.createOrUpdateApplication(applicationTemplateVO);
    }

    @Override
    public ApplicationInstanceVO deployApplicationInstance(ApplicationDeployRequest applicationDeployRequest) {
        var actualVo = getApplicationByName(applicationDeployRequest.getName());
        ApplicationDeployRequestWrapper applicationDeployRequestWrapper = new ApplicationDeployRequestWrapper(
                applicationDeployRequest.getVersion(),
                actualVo,
                applicationDeployRequest.getAlias(),
                applicationDeployRequest.getAttributes(),
                applicationDeployRequest.getResources());
        return applicationInstanceService.deployInstance(applicationDeployRequestWrapper);
    }

    @Override
    public SettingsVO setSettings(SettingsVO setting) {
        return settingsService.setSettings(setting);
    }

    @Override
    public SettingsVO getSettings() {
        return settingsService.getSettings();
    }

    @Override
    public ApplicationInstanceVO stopApplication(Long id) {
        return applicationInstanceManager.stopApplication(getSingleInstanceById(id));
    }

    @Override
    public ApplicationInstanceVO startApplication(Long id) {
        return applicationInstanceManager.startApplication(getSingleInstanceById(id));
    }

    @Override
    public ApplicationInstanceVO restartApplication(Long id) {
        return applicationInstanceManager.restartApplication(getSingleInstanceById(id));
    }
}
