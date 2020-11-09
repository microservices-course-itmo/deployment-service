package com.wine.to.up.deployment.service.service;



import com.wine.to.up.deployment.service.vo.ApplicationDeployRequest;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;

import java.util.List;

public interface DeploymentService {

     List<ApplicationInstanceVO> getMultipleInstancesByAppId(Long templateId);

     ApplicationInstanceVO getSingleInstanceById(long id);

     ApplicationTemplateVO getApplicationByName(String name);

     ApplicationTemplateVO createApplicationTemplate(ApplicationTemplateVO applicationTemplateVO);

     ApplicationInstanceVO deployApplicationInstance(ApplicationDeployRequest applicationDeployRequest);
}
