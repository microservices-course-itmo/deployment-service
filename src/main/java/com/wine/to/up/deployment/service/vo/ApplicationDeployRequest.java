package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationDeployRequest {
    private final String name;
    private final String version;
    private final String alias;
    private final Attributes attributes;
    private final Resources resources;

    @JsonCreator
    public ApplicationDeployRequest(@JsonProperty("name") final String name,
                                    @JsonProperty("version") final String version,
                                    @JsonProperty("alias") final String alias,
                                    @JsonProperty("attributes") final Attributes attributes,
                                    @JsonProperty("resources") final Resources resources) {
        this.name = name;
        this.version = version;
        this.alias = alias;
        this.attributes = attributes;
        this.resources = resources;
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

    public Attributes getAttributes() {
        return attributes;
    }

    public Resources getResources() {
        return resources;
    }
}
