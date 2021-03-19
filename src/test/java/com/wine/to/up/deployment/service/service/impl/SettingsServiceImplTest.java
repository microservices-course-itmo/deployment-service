package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.SettingsRepository;
import com.wine.to.up.deployment.service.entity.Settings;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SettingsServiceImplTest {

    @Mock
    SettingsRepository mockedRepository;

    @InjectMocks
    SettingsServiceImpl settingsService;

    @Test
    void setSettingsTest() {
        SettingsVO settingsVO = SettingsVO.builder()
                .dockerAddress("1")
                .versionRegistry("1")
                .imageRegistry("1")
                .build();
        Settings settingsEntity = new Settings(settingsVO.getDockerAddress(), settingsVO.getVersionRegistry(), settingsVO.getImageRegistry());
        when(mockedRepository.save(any())).thenReturn(settingsEntity);
        settingsService.setSettings(settingsVO);
        verify(mockedRepository, atLeast(1)).save(any());
    }

    @Test
    @DisplayName("Should get valid data")
    void getSettingsTestValid() {
        Settings testSettings = new Settings("1", "2", "3", "4");
        when(mockedRepository.findById(any())).thenReturn(java.util.Optional.of(testSettings));
        assertEquals("2", settingsService.getSettings().getDockerAddress());
    }

    @Test
    @DisplayName("Should get orElse data when settings not found")
    void getSettingsTestElse() {
        assertEquals("unix:///var/run/docker.sock", settingsService.getSettings().getDockerAddress());
    }
}