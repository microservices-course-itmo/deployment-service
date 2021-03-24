package com.wine.to.up.deployment.service.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.wine.to.up.deployment.service.entity.Attributes;
import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationInstanceVO {

    private final Long id;

    private final String appId;

    private final Long templateId;

    private final String version;

    private final Long dateCreated;

    private final String createdBy;

    private final String alias;

    private final String url;

    private final ApplicationInstanceStatus status;

    private final Attributes attributes;

    private final Resources resources;

    ApplicationInstanceVO(Long id, String appId, Long templateId, String version, Long dateCreated, String createdBy,
                          String alias, String url, ApplicationInstanceStatus status, Attributes attributes, final Resources resources) {
        this.id = id;
        this.appId = appId;
        this.templateId = templateId;
        this.version = version;
        this.dateCreated = dateCreated;
        this.createdBy = createdBy;
        this.alias = alias;
        this.url = url;
        this.status = status;
        this.attributes = attributes;
        this.resources = resources;
    }

    public static ApplicationInstanceVOBuilder builder() {
        return new ApplicationInstanceVOBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getAppId() {
        if (this.appId != null) {
            return this.appId;
        } else {
            return "";
        }
    }

    public Long getTemplateId() {
        return this.templateId;
    }

    public String getVersion() {
        if (this.version != null) {
            return this.version;
        } else {
            return "";
        }
    }

    public Long getDateCreated() {
        return this.dateCreated;
    }

    public String getCreatedBy() {
        if (this.createdBy != null) {
            return this.createdBy;
        } else {
            return "";
        }
    }

    public String getAlias() {
        if (this.alias != null) {
            return this.alias;
        } else {
            return "";
        }
    }

    public String getUrl() {
        if (this.url != null) {
            return this.url;
        } else {
            return "";
        }
    }

    public ApplicationInstanceStatus getStatus() {
        if (this.status != null) {
            return this.status;
        } else {
            return ApplicationInstanceStatus.STOPPED;
        }
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public String toString() {
        return "ApplicationInstanceVO(id=" + this.getId() + ", appId=" + this.getAppId()
                + ", templateId=" + this.getTemplateId() + ", version=" + this.getVersion()
                + ", dateCreated=" + this.getDateCreated() + ", createdBy=" + this.getCreatedBy()
                + ", alias=" + this.getAlias() + ", url=" + this.getUrl()
                + ", status=" + this.getStatus() + ", attributes=" + this.getAttributes() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ApplicationInstanceVO)) return false;
        final ApplicationInstanceVO other = (ApplicationInstanceVO) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$appId = this.getAppId();
        final Object other$appId = other.getAppId();
        if (this$appId == null ? other$appId != null : !this$appId.equals(other$appId)) return false;
        final Object this$templateId = this.getTemplateId();
        final Object other$templateId = other.getTemplateId();
        if (this$templateId == null ? other$templateId != null : !this$templateId.equals(other$templateId))
            return false;
        final Object this$version = this.getVersion();
        final Object other$version = other.getVersion();
        if (this$version == null ? other$version != null : !this$version.equals(other$version)) return false;
        final Object this$dateCreated = this.getDateCreated();
        final Object other$dateCreated = other.getDateCreated();
        if (this$dateCreated == null ? other$dateCreated != null : !this$dateCreated.equals(other$dateCreated))
            return false;
        final Object this$createdBy = this.getCreatedBy();
        final Object other$createdBy = other.getCreatedBy();
        if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false;
        final Object this$alias = this.getAlias();
        final Object other$alias = other.getAlias();
        if (this$alias == null ? other$alias != null : !this$alias.equals(other$alias)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$attributes = this.getAttributes();
        final Object other$attributes = other.getAttributes();
        if (this$attributes == null ? other$attributes != null : !this$attributes.equals(other$attributes)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ApplicationInstanceVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $appId = this.getAppId();
        result = result * PRIME + ($appId == null ? 43 : $appId.hashCode());
        final Object $templateId = this.getTemplateId();
        result = result * PRIME + ($templateId == null ? 43 : $templateId.hashCode());
        final Object $version = this.getVersion();
        result = result * PRIME + ($version == null ? 43 : $version.hashCode());
        final Object $dateCreated = this.getDateCreated();
        result = result * PRIME + ($dateCreated == null ? 43 : $dateCreated.hashCode());
        final Object $createdBy = this.getCreatedBy();
        result = result * PRIME + ($createdBy == null ? 43 : $createdBy.hashCode());
        final Object $alias = this.getAlias();
        result = result * PRIME + ($alias == null ? 43 : $alias.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $attributes = this.getAttributes();
        result = result * PRIME + ($attributes == null ? 43 : $attributes.hashCode());
        return result;
    }

    public Resources getResources() {
        return resources;
    }

    public static class ApplicationInstanceVOBuilder {
        private Long id;
        private String appId;
        private Long templateId;
        private String version;
        private Long dateCreated;
        private String createdBy;
        private String alias;
        private String url;
        private ApplicationInstanceStatus status;
        private Attributes attributes;
        private Resources resources;

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

        public ApplicationInstanceVOBuilder dateCreated(Long dateCreated) {
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

        public ApplicationInstanceVOBuilder attributes(Attributes attributes) {
            this.attributes = attributes;
            return this;
        }

        public ApplicationInstanceVOBuilder resources(Resources resources) {
            this.resources = resources;
            return this;
        }

        public ApplicationInstanceVO build() {
            return new ApplicationInstanceVO(id, appId, templateId, version, dateCreated, createdBy,
                    alias, url, status, attributes, resources);
        }

        public String toString() {
            return "ApplicationInstanceVO.ApplicationInstanceVOBuilder(id=" + this.id + ", appId=" + this.appId
                    + ", templateId=" + this.templateId + ", version=" + this.version + ", dateCreated="
                    + this.dateCreated + ", createdBy=" + this.createdBy + ", alias=" + this.alias
                    + ", url=" + this.url + ", status=" + this.status + ", attributes=" + this.attributes + ")";
        }
    }
}