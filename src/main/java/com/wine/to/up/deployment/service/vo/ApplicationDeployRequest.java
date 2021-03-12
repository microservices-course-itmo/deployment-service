package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationDeployRequest {
    private final String name;
    private final String version;
    private final String alias;

    @JsonCreator
    public ApplicationDeployRequest(@JsonProperty("name") final String name, @JsonProperty("version") final String version, @JsonProperty("alias") final String alias) {
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
