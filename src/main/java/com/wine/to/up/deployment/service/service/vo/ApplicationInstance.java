package com.wine.to.up.deployment.service.service.vo;

import java.net.URI;
import java.util.Map;

public interface ApplicationInstance {

    String getAppId();

    int getTemplateId();

    String getVersion();
    
    int getContainerId();

    int getDateCreated();

    String getUserCreated();

    String getStatus();

    default String getInstanceId() {
        return null;
    }

    String getServiceId();

    String getHost();

    int getPort();

    boolean isSecure();

    URI getUri();

    Map<String, String> getMetadata();

    default String getScheme() {
        return null;
    }

}