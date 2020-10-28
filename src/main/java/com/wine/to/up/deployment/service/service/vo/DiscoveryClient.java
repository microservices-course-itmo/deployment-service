package com.wine.to.up.deployment.service.service.vo;

import java.util.List;

public interface  DiscoveryClient {

    /**
     * A human-readable description of the implementation, used in HealthIndicator.
     * @return The description.
     */
    String description();
    /**
     * Gets all ServiceInstances associated with a particular serviceId.
     * @param serviceId The serviceId to query.
     * @return A List of ServiceInstance.
     */
    List<ApplicationInstance> getInstances(String serviceId);

    /**
     * @return All known service IDs.
     */
    List<String> getServices();
}
