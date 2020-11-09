package com.wine.to.up.deployment.service.entity;

import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "templates")
public class ApplicationInstance {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    private Long id;
    private String appId;
    private Long templateId;
    private String version;
    private LocalDateTime dateCreated;
    private String userCreated;
    private ApplicationInstanceStatus status;
    private String alias;
    private String url;

    public ApplicationInstance(final Long id, final String appId, final Long templateId, final String version, final LocalDateTime dateCreated, final String userCreated, final ApplicationInstanceStatus status, final String url, final String alias) {
        this.id = id;
        this.appId = appId;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final LocalDateTime dateCreated) {
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
