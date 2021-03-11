package com.wine.to.up.deployment.service.components;

import com.wine.to.up.commonlib.metrics.CommonMetricsCollector;
import org.springframework.stereotype.Component;

@Component
public class DeploymentServiceMetricsCollector extends CommonMetricsCollector {

    public static final String SERVICE_NAME = "deployment_service";

    public DeploymentServiceMetricsCollector() {
        this(SERVICE_NAME);
    }

    public DeploymentServiceMetricsCollector(String serviceName) {
        super(serviceName);
    }

}
