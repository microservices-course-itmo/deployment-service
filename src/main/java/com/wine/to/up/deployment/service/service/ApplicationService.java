package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.vo.ApplicationTemplateVO;

public interface ApplicationService {
    ApplicationTemplateVO getApplicationTemplate(Long id);
    ApplicationTemplateVO createApplication(ApplicationTemplateVO applicationTemplateVO);
}
