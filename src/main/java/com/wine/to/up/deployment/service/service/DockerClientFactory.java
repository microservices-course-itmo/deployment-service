package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.wine.to.up.deployment.service.entity.DockerSettings;
import org.springframework.stereotype.Component;

@Component
public class DockerClientFactory {

    public DockerClient getDockerClient(String dockerAddress) {
            DockerSettings dockerSettings = new DockerSettings();
            dockerAddress = dockerSettings.getDockerAddress();
        return DockerClientBuilder.getInstance().build();
    }
}