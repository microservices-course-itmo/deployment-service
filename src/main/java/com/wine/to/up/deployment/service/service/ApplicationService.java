package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;

import java.util.List;

public interface ApplicationService {
    ApplicationTemplateVO getApplicationTemplate(String name);

    ApplicationTemplateVO getApplicationTemplate(Long id);

    ApplicationTemplateVO createOrUpdateApplication(ApplicationTemplateVO applicationTemplateVO);

    List<String> getAllNames();

    void removeEntity(String name);
}
