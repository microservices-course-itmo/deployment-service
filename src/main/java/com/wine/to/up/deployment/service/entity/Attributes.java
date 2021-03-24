package com.wine.to.up.deployment.service.entity;

public class Attributes {

    private final boolean isTestInstance;

    public Attributes(boolean isTestInstance) {
        this.isTestInstance = isTestInstance;
    }

    public boolean isTestInstance() {
        return isTestInstance;
    }

}
