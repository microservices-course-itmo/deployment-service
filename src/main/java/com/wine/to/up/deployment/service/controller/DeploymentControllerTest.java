package com.wine.to.up.deployment.service.controller;

import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.ApplicationService;
import com.wine.to.up.deployment.service.service.DeploymentService;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.internal.util.Assert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class DeploymentControllerTest {

    private MockMvc mockMvc;

    @Mock
    DeploymentService deploymentService;

    @Mock
    private ApplicationImportService applicationImportService;

    @Mock
    private ApplicationInstanceService applicationInstanceService;

    @Mock
    private ApplicationService applicationTemplateService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);// this is needed for inititalization of mocks, if you use @Mock
        //DeploymentController controller = new DeploymentController(applicationTemplateService);
        //mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void deleteApplicationTemplate() {
        Mockito.when(applicationTemplateService.removeEntity("ASD")).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders.delete("/application", 10001L))
                .andExpect(status().isOk());
    }

    @Test
    void deleteApplicationInstance() {
        Mockito.when(deploymentService.removeApplicationInstanceById(1L)).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders.delete("/applicationInstance", 10001L))
                .andExpect(status().isOk());
    }
}