package com.wine.to.up.deployment.service.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationInstanceVO   {

    private final Long id;

    private final String appId;

    private final Long templateId;

    private final String version;

    private final String containerId;

    private final String dateCreated;

    private final String createdBy;

    private final String alias;

    private final String url;

    private final ApplicationInstanceStatus status;

    ApplicationInstanceVO(Long id, String appId, Long templateId, String version, String containerId, String dateCreated, String createdBy, String alias, String url, ApplicationInstanceStatus status) {
        this.id = id;
        this.appId = appId;
        this.templateId = templateId;
        this.version = version;
        this.containerId = containerId;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.alias = alias;
        this.url = url;
        this.status = status;
    }

    public static ApplicationInstanceVOBuilder builder() {
        return new ApplicationInstanceVOBuilder();
    }

    public static class ApplicationInstanceVOBuilder {
        private Long id;
        private String appId;
        private Long templateId;
        private String version;
        private String containerId;
        private String dateCreated;
        private String createdBy;
        private String alias;
        private String url;
        private ApplicationInstanceStatus status;

        ApplicationInstanceVOBuilder() {
        }

        public ApplicationInstanceVOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ApplicationInstanceVOBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public ApplicationInstanceVOBuilder templateId(Long templateId) {
            this.templateId = templateId;
            return this;
        }

        public ApplicationInstanceVOBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ApplicationInstanceVOBuilder containerId(String containerId) {
            this.containerId = containerId;
            return this;
        }

        public ApplicationInstanceVOBuilder dateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public ApplicationInstanceVOBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public ApplicationInstanceVOBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public ApplicationInstanceVOBuilder url(String url) {
            this.url = url;
            return this;
        }

        public ApplicationInstanceVOBuilder status(ApplicationInstanceStatus status) {
            this.status = status;
            return this;
        }

        public ApplicationInstanceVO build() {
            return new ApplicationInstanceVO(id, appId, templateId, version, containerId, dateCreated, createdBy, alias, url, status);
        }

        public String toString() {
            return "ApplicationInstanceVO.ApplicationInstanceVOBuilder(id=" + this.id + ", appId=" + this.appId + ", templateId=" + this.templateId + ", version=" + this.version + ", containerId=" + this.containerId + ", dateCreated=" + this.dateCreated + ", createdBy=" + this.createdBy + ", alias=" + this.alias + ", url=" + this.url + ", status=" + this.status + ")";
        }
    }
}
