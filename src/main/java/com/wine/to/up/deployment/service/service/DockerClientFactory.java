package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.stereotype.Component;

@Component
public class DockerClientFactory {
    public DockerClient getDockerClient(String username, String password) {

        return DockerClientBuilder.getInstance().build();
    }
}