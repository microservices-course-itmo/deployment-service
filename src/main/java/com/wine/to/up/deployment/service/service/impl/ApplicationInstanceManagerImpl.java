package com.wine.to.up.deployment.service.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Service;
import com.github.dockerjava.api.model.ServiceModeConfig;
import com.github.dockerjava.api.model.ServiceReplicatedModeOptions;
import com.github.dockerjava.api.model.ServiceSpec;
import com.wine.to.up.deployment.service.service.ApplicationInstanceManager;
import com.wine.to.up.deployment.service.service.DockerClientFactory;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Objects;

@org.springframework.stereotype.Service
public class ApplicationInstanceManagerImpl implements ApplicationInstanceManager {

    private DockerClientFactory dockerClientFactory;

    @Autowired
    public void setDockerClientFactory(DockerClientFactory dockerClientFactory) {
        this.dockerClientFactory = dockerClientFactory;
    }

    //No error, not working
    public void startApplication(@NotNull ApplicationInstanceVO applicationInstanceVO) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient();
        Service dockerService = dockerClient.inspectServiceCmd(applicationInstanceVO.getAppId()).exec();
        ServiceSpec serviceSpec = new ServiceSpec();
        ServiceSpec betterView = dockerService.getSpec();
        if (betterView != null && betterView.getMode() != null && betterView.getMode().getReplicated() != null) {
            if (betterView.getMode().getReplicated().getReplicas() == 0) {
                ServiceModeConfig serviceModeConfig = betterView.getMode();
                ServiceReplicatedModeOptions serviceReplicatedModeOptions = Objects.requireNonNull(betterView.getMode().getReplicated()).withReplicas(1);
                serviceModeConfig = serviceModeConfig.withReplicated(serviceReplicatedModeOptions);
                serviceSpec = serviceSpec.withMode(serviceModeConfig);
                if (dockerService.getVersion() != null) {
                    dockerClient.updateServiceCmd(dockerService.getId(), serviceSpec).withVersion(dockerService.getVersion().getIndex()).exec();
                }
            }
        }
    }

    //ERROR: BadRequestException: Status 400: mismatched Runtime and *Spec fields
    public void stopApplication(@NotNull ApplicationInstanceVO applicationInstanceVO) {
        DockerClient dockerClient = dockerClientFactory.getDockerClient();
        Service dockerService = dockerClient.inspectServiceCmd(applicationInstanceVO.getAppId()).exec();
        ServiceSpec betterView = dockerService.getSpec();
        ServiceModeConfig serviceModeConfig = betterView.getMode();
        ServiceReplicatedModeOptions serviceReplicatedModeOptions = Objects.requireNonNull(betterView.getMode().getReplicated()).withReplicas(0);
        serviceModeConfig = serviceModeConfig.withReplicated(serviceReplicatedModeOptions);
        ServiceSpec serviceSpec = new ServiceSpec().withMode(serviceModeConfig);
        if (dockerService.getVersion() != null) {
            dockerClient.updateServiceCmd(dockerService.getId(), serviceSpec).withVersion(dockerService.getVersion().getIndex()).exec();
        }
    }

    public void restartApplication (ApplicationInstanceVO applicationInstanceVO) {
        stopApplication(applicationInstanceVO);
        startApplication(applicationInstanceVO);
    }
}
