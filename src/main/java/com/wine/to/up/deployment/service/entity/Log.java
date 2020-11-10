package com.wine.to.up.deployment.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs")
public class Log {

    @Transient
    public static final String SEQUENCE_NAME = "log_sequence";

    private Long id;
    private Long createdDate;
    private String log;
    private String user;
    private String templateName;
    private Long templateId;

    public Log() {
    }

    public Log(final Long id, final Long createdDate, final String log, final String user, final String templateName, final Long templateId) {
        this.id = id;
        this.createdDate = createdDate;
        this.log = log;
        this.user = user;
        this.templateName = templateName;
        this.templateId = templateId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(final Long templateId) {
        this.templateId = templateId;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }

    public String getLog() {
        return this.log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }
}
