package com.wine.to.up.deployment.service.service;



import com.wine.to.up.deployment.service.service.vo.ApplicationInstance;

import java.util.List;

public interface DeploymentService {
    void test();

    List<ApplicationInstance> getInstances(String serviceId);
}
