package com.wine.to.up.deployment.service.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Service;
import com.github.dockerjava.api.model.ServiceModeConfig;
import com.github.dockerjava.api.model.ServiceReplicatedModeOptions;
import com.github.dockerjava.api.model.ServiceSpec;
import com.wine.to.up.deployment.service.service.ApplicationInstanceManager;
import com.wine.to.up.deployment.service.service.DockerClientFactory;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ApplicationInstanceManagerImpl implements ApplicationInstanceManager {

    private DockerClientFactory dockerClientFactory;

    @Autowired
    public void setDockerClientFactory(DockerClientFactory dockerClientFactory) {
        this.dockerClientFactory = dockerClientFactory;
    }

    public void startApplication(@NotNull ApplicationInstanceVO applicationInstanceVO) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient();
        Service dockerService = dockerClient.inspectServiceCmd(applicationInstanceVO.getAppId()).exec();
        ServiceSpec spec = dockerService.getSpec();
        ServiceModeConfig serviceModeConfig = spec.getMode();

        if (serviceModeConfig != null) {
            ServiceReplicatedModeOptions serviceReplicatedModeOptions = serviceModeConfig
                    .getReplicated()
                    .withReplicas(1);
            serviceModeConfig = serviceModeConfig.withReplicated(serviceReplicatedModeOptions);
            spec = spec.withMode(serviceModeConfig);
        }


        if (dockerService.getVersion() != null) {
            dockerClient.updateServiceCmd(dockerService.getId(), spec)
                    .withVersion(dockerService.getVersion().getIndex()).exec();
        }
    }

    public void stopApplication(@NotNull ApplicationInstanceVO applicationInstanceVO) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient();
        Service dockerService = dockerClient.inspectServiceCmd(applicationInstanceVO.getAppId()).exec();
        ServiceSpec spec = dockerService.getSpec();
        ServiceModeConfig serviceModeConfig = spec.getMode();

        if (serviceModeConfig != null) {
            ServiceReplicatedModeOptions serviceReplicatedModeOptions = serviceModeConfig
                    .getReplicated()
                    .withReplicas(0);
            serviceModeConfig = serviceModeConfig.withReplicated(serviceReplicatedModeOptions);
            spec = spec.withMode(serviceModeConfig);
        }


        if (dockerService.getVersion() != null) {
            dockerClient.updateServiceCmd(dockerService.getId(), spec)
                    .withVersion(dockerService.getVersion().getIndex()).exec();
        }
    }

    public void restartApplication (ApplicationInstanceVO applicationInstanceVO) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient();
        Service dockerService = dockerClient.inspectServiceCmd(applicationInstanceVO.getAppId()).exec();
        ServiceSpec spec = dockerService.getSpec();

        if (dockerService.getVersion() != null) {
            dockerClient.updateServiceCmd(dockerService.getId(), spec)
                    .withVersion(dockerService.getVersion().getIndex()).exec();
        }
    }
}
