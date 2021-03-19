package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.commonlib.security.AuthenticationProvider;
import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.EnvironmentVariable;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.LogService;
import com.wine.to.up.deployment.service.service.SequenceGeneratorService;
import com.wine.to.up.deployment.service.service.ServiceVersionProvider;
import com.wine.to.up.deployment.service.vo.ApplicationInstanceVO;
import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;
import com.wine.to.up.deployment.service.vo.LogVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.ws.rs.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {

    @Mock ApplicationTemplateRepository applicationTemplateRepository;
    @Mock ApplicationInstanceService applicationInstanceService;
    @Mock SequenceGeneratorService sequenceGeneratorService;
    @Mock LogService logService;
    @Mock ServiceVersionProvider serviceVersionProvider;
    @Mock MongoTemplate mongoTemplate;
    @Mock ApplicationTemplateVO mockedTemplateVO;
    @Mock ApplicationTemplate mockedTemplate;
    @Mock AuthenticationProvider authenticationProvider;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Test
    void getApplicationTemplateTest() {
        Map<String, String> portMappings = new HashMap<String, String>();
        List<String> volumes = new ArrayList<>();
        List<EnvironmentVariable> environmentVariables = new ArrayList<>();
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(1L, "createdBy", "name", portMappings,
                volumes, environmentVariables, "description", "baseBranch");
        when(applicationTemplateRepository.findFirstByNameOrderByIdDesc(any())).thenReturn(Optional.of(applicationTemplate));
        when(applicationTemplateRepository.findById(any())).thenReturn(Optional.of(applicationTemplate));
        assertEquals("createdBy", applicationService.getApplicationTemplate("name").getCreatedBy());
        assertEquals("createdBy", applicationService.getApplicationTemplate(1L).getCreatedBy());
    }

    @Test
    @Disabled("Initiate mongoTemplate")
    void getAllNames() {
        applicationService.getAllNames();
        verify(mongoTemplate, atLeast(1)).getCollection("templates");
    }

    @Test
    @Disabled("applicationTemplate as list")
    void removeEntity() {
        Map<String, String> portMappings = new HashMap<String, String>();
        List<String> volumes = new ArrayList<>();
        List<EnvironmentVariable> environmentVariables = new ArrayList<>();
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(1L, "createdBy", "name", portMappings,
                volumes, environmentVariables, "description", "baseBranch");
        when(applicationTemplateRepository.findAllByName(any())).thenReturn((List<ApplicationTemplate>) applicationTemplate);
        applicationService.removeEntity("name");
        verify(applicationTemplateRepository, atLeast(1)).deleteById(applicationTemplate.getId());
    }

    @Test
    @Disabled("AuthenticationProvider mock")
    void createOrUpdateApplication() {
        List<EnvironmentVariable> environmentVariables = new ArrayList<>();
        List<String> volumes = new ArrayList<>();
        Map<String, String> ports = new HashMap<String, String>();
        List<String> versions = new ArrayList<>();
        List<ApplicationInstanceVO> instances = new ArrayList<>();
        List<LogVO> logs = new ArrayList<>();
        ApplicationTemplateVO applicationTemplateVO = ApplicationTemplateVO.builder()
                .id(1L)
                .name("name")
                .description("description")
                .templateVersion(2L)
                .alias("alias")
                .environmentVariables(environmentVariables)
                .volumes(volumes)
                .ports(ports)
                .versions(versions)
                .instances(instances)
                .logs(logs)
                .createdBy("createdBy")
                .dateCreated(3L)
                .baseBranch("baseBranch")
                .build();
        ApplicationTemplate applicationTemplate = new ApplicationTemplate(applicationTemplateVO.getTemplateVersion(),
                applicationTemplateVO.getCreatedBy(), applicationTemplateVO.getName(), applicationTemplateVO.getPorts(),
                applicationTemplateVO.getVolumes(), applicationTemplateVO.getEnvironmentVariables(),
                applicationTemplateVO.getDescription(),
                applicationTemplateVO.getBaseBranch() != null ? applicationTemplateVO.getBaseBranch() : "dev");
        when(applicationTemplateRepository.save(any())).thenReturn(applicationTemplate);
        //TODO: FIX authenticationProvider mock
        when(mockedTemplate.getCreatedBy()).thenReturn("createdBy");
        when(AuthenticationProvider.getUser().getId().toString()).thenReturn("createdBy");
        applicationService.createOrUpdateApplication(mockedTemplateVO);
        assertEquals(applicationTemplateVO.getCreatedBy(), mockedTemplateVO.getCreatedBy());
    }
}