package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.dao.SettingsRepository;
import com.wine.to.up.deployment.service.entity.Settings;
import com.wine.to.up.deployment.service.service.SettingsService;
import com.wine.to.up.deployment.service.vo.SettingsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Kosturenko
 */
@Service
public class SettingsServiceImpl implements SettingsService {

    private SettingsRepository settingsRepository;

    @Autowired
    public void setDockerSettingsRepository(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @Override
    public SettingsVO setSettings(SettingsVO settings) {
        final Settings settingsEntity = new Settings(settings.getDockerAddress(), settings.getRegistry());
        return entityToView(
                settingsRepository.save(settingsEntity)
        );
    }

    @Override
    public SettingsVO getSettings() {
        return entityToView(settingsRepository
                .findById(Settings.SINGLETON_ID)
                .orElse(new Settings("unix:///var/run/docker.sock", "registry:5000")));
    }

    private SettingsVO entityToView(Settings entity) {
        return SettingsVO.builder()
                .dockerAddress(entity.getDockerAddress())
                .registry(entity.getRegistry())
                .build();
    }
}
