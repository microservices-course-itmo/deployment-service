package com.wine.to.up.deployment.service.service;



import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;

import java.util.List;

public interface DeploymentService {

     List<ApplicationInstanceVO> getMultipleInstancesByAppId(Long templateId);

     ApplicationInstanceVO getSingleInstanceById(long id);
}
