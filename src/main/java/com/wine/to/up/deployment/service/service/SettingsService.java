package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.vo.SettingsVO;

/**
 * @author Alexander Kosturenko
 */
public interface SettingsService {
    SettingsVO setSettings(SettingsVO settings);

    SettingsVO getSettings();
}
