package com.wine.to.up.deployment.service.entity;

import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "templates")
public class ApplicationInstance {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    private Long id;
    private String appId;
    private Long templateId;
    private String version;
    private String containerId;
    private String dateCreated;
    private String userCreated;
    private ApplicationInstanceStatus status;
    private String url;

    public ApplicationInstance(final Long id, final String appId, final Long templateId, final String version, final String containerId, final String dateCreated, final String userCreated, final ApplicationInstanceStatus status, final String url) {
        this.id = id;
        this.appId = appId;
        this.templateId = templateId;
        this.version = version;
        this.containerId = containerId;
        this.dateCreated = dateCreated;
        this.userCreated = userCreated;
        this.status = status;
        this.url = url;
    }

    public ApplicationInstance() {

    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(final String appId) {
        this.appId = appId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(final Long templateId) {
        this.templateId = templateId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(final String containerId) {
        this.containerId = containerId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(final String userCreated) {
        this.userCreated = userCreated;
    }

    public ApplicationInstanceStatus getStatus() {
        return status;
    }

    public void setStatus(final ApplicationInstanceStatus status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }
}
