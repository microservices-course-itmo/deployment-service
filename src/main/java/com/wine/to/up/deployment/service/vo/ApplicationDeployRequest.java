package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequest {
    private final String name;
    private final String version;

    public ApplicationDeployRequest(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
