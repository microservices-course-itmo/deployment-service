package com.wine.to.up.deployment.service.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "docker_settings")
@Getter
public class Settings {

    @Id
    private String id;

    private String dockerAddress;
    private String registry;

    public Settings() {
    }

    public Settings(String dockerAddress, String registry) {
        this.id = "docker_settings";
        this.dockerAddress = dockerAddress;
        this.registry = registry;
    }

}
