package com.wine.to.up.deployment.service.vo;

public class ApplicationDeployRequestWrapper {
    private final String version;
    private final String alias;
    private final ApplicationTemplateVO applicationTemplateVO;
    private final Attributes attributes;
    private final Resources resources;

    public ApplicationDeployRequestWrapper(final String version, final ApplicationTemplateVO applicationTemplateVO, final String alias,
                                           final Attributes attributes, final Resources resources) {
        this.version = version;
        this.applicationTemplateVO = applicationTemplateVO;
        this.alias = alias;
        this.attributes = attributes;
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

    public Attributes getAttributes() {
        return attributes;
    }

    public Resources getResources() {
        return resources;
    }
}
