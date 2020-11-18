package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.SettingsVO;

import java.util.List;

public interface DeploymentService {

     List<ApplicationInstanceVO> getInstancesByAppName(String templateName);

     ApplicationInstanceVO getSingleInstanceById(Long id);

     ApplicationTemplateVO getApplicationByName(String name);

     ApplicationTemplateVO getApplicationById(Long id);

     ApplicationTemplateVO createOrUpdateApplicationTemplate(ApplicationTemplateVO applicationTemplateVO);

     ApplicationInstanceVO deployApplicationInstance(ApplicationDeployRequest applicationDeployRequest);

     List<String> getAllNames();

     SettingsVO setSettings(SettingsVO settings);

     SettingsVO getSettings();
}
