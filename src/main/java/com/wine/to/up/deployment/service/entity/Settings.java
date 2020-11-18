package com.wine.to.up.deployment.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "docker_settings")
public class Settings {
    public static final String SINGLETON_ID = "SINGLETON";

    private String id = SINGLETON_ID;
    private String dockerAddress;
    private String registry;

    public Settings() {
    }

    public Settings(String dockerAddress, String registry) {
        this.dockerAddress = dockerAddress;
        this.registry = registry;
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
