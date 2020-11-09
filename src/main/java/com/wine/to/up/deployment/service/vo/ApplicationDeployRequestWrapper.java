package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequestWrapper {
    private final String version;
    private final String alias;
    private final ApplicationTemplateVO applicationTemplateVO;

    public ApplicationDeployRequestWrapper(String version, ApplicationTemplateVO applicationTemplateVO, String alias) {
        this.version = version;
        this.applicationTemplateVO = applicationTemplateVO;
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public String getVersion() {
        return version;
    }

    public ApplicationTemplateVO getApplicationTemplateVO() {
        return applicationTemplateVO;
    }
}
