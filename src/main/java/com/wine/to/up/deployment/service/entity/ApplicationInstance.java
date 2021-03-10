package com.wine.to.up.deployment.service.entity;

import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "instances")
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
        if (id != null) {
            return this.id;
        } else {
            return 1337L;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        if (appId != null) {
            return this.appId;
        } else {
            return "";
        }
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTemplateName() {
        if (templateName != null) {
            return this.templateName;
        } else {
            return "";
        }
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getTemplateId() {
        if (templateId != null) {
            return  this.templateId;
        } else {
            return 1337L;
        }
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getVersion() {
        if (version != null) {
            return  this.version;
        } else {
            return "";
        }
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getDateCreated() {
        if (dateCreated != null) {
            return  this.dateCreated;
        } else {
            return 1337L;
        }
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserCreated() {
        if (userCreated!= null) {
            return  this.userCreated;
        } else {
            return "";
        }
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public ApplicationInstanceStatus getStatus() {
        if (status!= null) {
            return  this.status;
        } else {
            return ApplicationInstanceStatus.STOPPED;
        }
    }

    public void setStatus(ApplicationInstanceStatus status) {
        this.status = status;
    }

    public String getAlias() {
        if (alias!= null) {
            return  this.alias;
        } else {
            return "";
        }
    }

    public String getUrl() {
        if (url!= null) {
            return  this.url;
        } else {
            return "";
        }
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}