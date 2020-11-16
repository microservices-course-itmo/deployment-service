package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.wine.to.up.deployment.service.entity.DockerSettings;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.DockerSettingsVO;
import org.springframework.stereotype.Component;

@Component
public class DockerClientFactory {

    public DockerSettingsVO getDockerClient(String dockerAddress) {
        return DockerSettingsVO.builder()
                .id("1")
                .dockerAddress(dockerAddress)
                .registry("1")
                .build();
    }
}