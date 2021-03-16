package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;

public interface ApplicationInstanceManager {

    ApplicationInstanceVO startApplication(ApplicationInstanceVO applicationInstanceVO);

    ApplicationInstanceVO stopApplication(ApplicationInstanceVO applicationInstanceVO);

    ApplicationInstanceVO restartApplication(ApplicationInstanceVO applicationInstanceVO);
}
