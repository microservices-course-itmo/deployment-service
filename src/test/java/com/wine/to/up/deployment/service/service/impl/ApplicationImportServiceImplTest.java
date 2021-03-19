package com.wine.to.up.deployment.service.service.impl;

import com.github.dockerjava.api.DockerClient;
import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository;
import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.service.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationImportServiceImplTest {

    @Mock ApplicationTemplateRepository applicationTemplateRepository;
    @Mock ApplicationInstanceService applicationInstanceService;
    @Mock SettingsService settingsService;
    @Mock ApplicationInstanceRepository applicationInstanceRepository;
    @Mock SequenceGeneratorService sequenceGeneratorService;
    @Mock ApplicationService applicationService;

    @Mock DockerClient dockerClient;
    @Mock DockerClientFactory dockerClientFactory;

    @InjectMocks
    ApplicationImportServiceImpl applicationImportService;

    @Test
    @Disabled("TODO")
    void importInstancesTest() {
        applicationImportService.importInstances();
    }
}