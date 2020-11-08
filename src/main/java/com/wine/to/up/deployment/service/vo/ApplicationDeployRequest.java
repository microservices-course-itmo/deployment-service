package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequest {
    private final String name;
    private final String version;
    private final String alias;

    public ApplicationDeployRequest(String name, String version, String alias) {
        this.name = name;
        this.version = version;
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }
}
