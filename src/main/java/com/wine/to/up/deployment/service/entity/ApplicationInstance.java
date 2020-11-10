package com.wine.to.up.deployment.service.entity;

import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "templates")
public class ApplicationInstance {

    @Transient
    public static final String SEQUENCE_NAME = "app_instance_sequence";

    @Id
    private Long id;
    private String appId;
    private String templateName;
    private Long templateId;
    private String version;
    private Long dateCreated;
    private String userCreated;
    private ApplicationInstanceStatus status;
    private String alias;
    private String url;

    public ApplicationInstance(final Long id, final String templateName, final String appId, final Long templateId, final String version, final Long dateCreated, final String userCreated, final ApplicationInstanceStatus status, final String url, final String alias) {
        this.id = id;
        this.appId = appId;
        this.templateName = templateName;
        this.templateId = templateId;
        this.version = version;
        this.dateCreated = dateCreated;
        this.userCreated = userCreated;
        this.status = status;
        this.url = url;
        this.alias = alias;
    }

    public ApplicationInstance() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserCreated() {
        return this.userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public ApplicationInstanceStatus getStatus() {
        return this.status;
    }

    public void setStatus(ApplicationInstanceStatus status) {
        this.status = status;
    }

    public String getAlias() {
        return this.alias;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
