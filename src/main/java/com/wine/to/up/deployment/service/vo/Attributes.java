package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {

    private final boolean isTestInstance;

    @JsonCreator
    public Attributes(@JsonProperty("testInstance") final boolean isTestInstance) {
        this.isTestInstance = isTestInstance;
    }

    public boolean isTestInstance() {
        return isTestInstance;
    }

}
