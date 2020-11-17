package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.SettingsRepository;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import com.wine.to.up.deployment.service.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeploymentServiceImpl implements DeploymentService {

    private final ApplicationInstanceService applicationInstanceService;
    private final ApplicationService applicationService;
    private SettingsRepository settingsRepository;

    @Autowired
    public void setDockerSettingsRepository(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Autowired
    public DeploymentServiceImpl(
            ApplicationInstanceService applicationInstanceService, ApplicationService applicationService
    ) {
        this.applicationInstanceService = applicationInstanceService;
        this.applicationService = applicationService;
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
                applicationDeployRequest.getAlias());
        return applicationInstanceService.deployInstance(applicationDeployRequestWrapper);
    }

    @Override
    public SettingsVO setSettings(SettingsVO setting) {
        SettingsVO settingsVO = SettingsVO.builder()
                .dockerAddress(setting.getDockerAddress())
                .registry(setting.getRegistry())
                .build();
        return settingsVO;
    }
}
