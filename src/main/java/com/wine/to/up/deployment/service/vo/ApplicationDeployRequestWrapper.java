package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequestWrapper {
    private final String version;
    private final String alias;
    private final ApplicationTemplateVO applicationTemplateVO;
    private final Resources resources;

    public ApplicationDeployRequestWrapper(String version, ApplicationTemplateVO applicationTemplateVO, String alias, final Resources resources) {
        this.version = version;
        this.applicationTemplateVO = applicationTemplateVO;
        this.alias = alias;
        this.resources = resources;
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

    public Resources getResources() {
        return resources;
    }
}
