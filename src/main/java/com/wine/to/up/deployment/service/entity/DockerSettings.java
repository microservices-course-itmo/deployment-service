package com.wine.to.up.deployment.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "docker_settings")
public class DockerSettings {

    @Id
    private  String id;
    private  String dockerAddress;
    private  String registry;

    public DockerSettings() {}

    public DockerSettings(String id, String dockerAddress, String registry) {
        this.id = id;
        this.dockerAddress = dockerAddress;
        this.registry = registry;
    }

    public String getId(){
        return id;
    }
    public String getDockerAddress() {
        return dockerAddress;
    }
    public String getRegistry() {
        return registry;
    }




}
