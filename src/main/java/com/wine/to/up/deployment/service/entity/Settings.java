package com.wine.to.up.deployment.service.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "docker_settings")
@Getter

public class Settings {
    public static final String SINGLETON_ID = "SINGLETON";

    @Id
    private final String id = SINGLETON_ID;
    private String dockerAddress;
    private String registry;

    public Settings() {
    }

    public Settings(String dockerAddress, String registry) {
        this.dockerAddress = dockerAddress;
        this.registry = registry;
    }

}
