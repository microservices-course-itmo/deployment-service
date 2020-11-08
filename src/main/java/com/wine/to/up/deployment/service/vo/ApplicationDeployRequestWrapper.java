package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequestWrapper {
    private String version;
    private ApplicationTemplateVO applicationTemplateVO;

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
