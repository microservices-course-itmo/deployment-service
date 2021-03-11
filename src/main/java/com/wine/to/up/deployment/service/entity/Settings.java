package com.wine.to.up.deployment.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "docker_settings")
public class Settings {
    public static final String SINGLETON_ID = "SINGLETON";

    private String id = SINGLETON_ID;
    private String dockerAddress;
    private String registry;
    private String unproxiedRegistry;

    public Settings(final String id, final String dockerAddress, final String registry, final String unproxiedRegistry) {
        this.id = id;
        this.dockerAddress = dockerAddress;
        this.registry = registry;
        this.unproxiedRegistry = unproxiedRegistry;
    }

    public Settings(final String dockerAddress, final String registry, final String unproxiedRegistry) {
        this.dockerAddress = dockerAddress;
        this.registry = registry;
        this.unproxiedRegistry = unproxiedRegistry;
    }

    public String getUnproxiedRegistry() {
        return unproxiedRegistry;
    }

    public Settings() {
    }

    @Id
    public String getId() {
        return this.id;
    }

    /**
     * Setter for Spring Data
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getDockerAddress() {
        return this.dockerAddress;
    }

    public void setDockerAddress(String dockerAddress) {
        this.dockerAddress = dockerAddress;
    }

    public String getRegistry() {
        return this.registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }
}
