package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequestWrapper {
    private final String version;
    private final ApplicationTemplateVO applicationTemplateVO;

    public ApplicationDeployRequestWrapper(String version, ApplicationTemplateVO applicationTemplateVO) {
        this.version = version;
        this.applicationTemplateVO = applicationTemplateVO;
    }

    public String getVersion() {
        return version;
    }

    public ApplicationTemplateVO getApplicationTemplateVO() {
        return applicationTemplateVO;
    }
}
