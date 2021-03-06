package com.wine.to.up.deployment.service.entity;

public class EnvironmentVariable {
    private String name;
    private String value;

    public EnvironmentVariable(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public EnvironmentVariable() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
