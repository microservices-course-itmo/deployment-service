package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;

public interface ApplicationInstanceManager {

    void startApplication(ApplicationInstanceVO applicationInstanceVO);

    void stopApplication(ApplicationInstanceVO applicationInstanceVO);

    void restartApplication(ApplicationInstanceVO applicationInstanceVO);
}
