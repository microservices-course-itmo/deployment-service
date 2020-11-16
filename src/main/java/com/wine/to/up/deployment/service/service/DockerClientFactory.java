package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.wine.to.up.deployment.service.entity.DockerSettings;
import org.springframework.stereotype.Component;

@Component
public class DockerClientFactory {

    public DockerClient getDockerClient(DockerSettings dockerSettings ) {
        return (DockerClient) DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(dockerSettings.getDockerAddress())
                .build();
    }
}