package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.wine.to.up.deployment.service.dao.SettingsRepository;
import com.wine.to.up.deployment.service.entity.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;

@Component
public class DockerClientFactory {

    private SettingsRepository settingsRepository;

    @Autowired
    public void setDockerSettingsRepository(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public DockerClient getDockerClient() {
        String settings = settingsRepository.findById(Settings.SINGLETON_ID)
                .orElseThrow(() -> new NotFoundException("Didn't manage to find setting by specified id"))
                .getDockerAddress();
        return DockerClientBuilder.getInstance(DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost((settings))
                .build()
        ).build();
    }
}