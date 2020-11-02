package com.wine.to.up.deployment.service.service;



import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;

import java.util.List;

public interface DeploymentService {
    void test();

     List<ApplicationInstanceVO> getMultipleInstancesByAppId(String appId);

     ApplicationInstanceVO getSingleInstanceById(long id);
}
