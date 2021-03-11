package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DockerClientFactory {

    private SettingsService settingsService;

    @Autowired
    public void setDockerSettingsRepository(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    public DockerClient getDockerClient() {
        return DockerClientBuilder.getInstance(DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(settingsService.getSettings().getDockerAddress())
                .build()
        ).build();
    }
}