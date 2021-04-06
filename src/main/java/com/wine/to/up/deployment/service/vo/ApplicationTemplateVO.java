package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wine.to.up.deployment.service.entity.EnvironmentVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationTemplateVO {
    private final Long id;
    private final String name;
    private final String description;
    private final Long templateVersion;
    private final String alias;
    private final List<EnvironmentVariable> environmentVariables;
    private final List<String> volumes;
    private final Map<String, String> ports;
    private final List<String> versions;
    private final List<ApplicationInstanceVO> instances;
    private final List<LogVO> logs;
    private final String createdBy;
    private final Long dateCreated;
    private final String baseBranch;

    @JsonCreator
    private ApplicationTemplateVO(@JsonProperty("id") final Long id,
                                  @JsonProperty("name") final String name,
                                  @JsonProperty("description") final String description,
                                  @JsonProperty("templateVersion") final Long templateVersion,
                                  @JsonProperty("alias") final String alias,
                                  @JsonProperty("environmentVariables") final List<EnvironmentVariable> environmentVariables,
                                  @JsonProperty("volumes") final List<String> volumes,
                                  @JsonProperty("ports") final Map<String, String> ports,
                                  @JsonProperty("versions") final List<String> versions,
                                  @JsonProperty("instances") final List<ApplicationInstanceVO> instances,
                                  @JsonProperty("logs") final List<LogVO> logs,
                                  @JsonProperty("createdBy") final String createdBy,
                                  @JsonProperty("dateCreated") final Long dateCreated,
                                  @JsonProperty("baseBranch") final String baseBranch) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.templateVersion = templateVersion;
        this.alias = alias;
        this.environmentVariables = environmentVariables;
        this.volumes = volumes;
        this.ports = ports;
        this.versions = versions;
        this.instances = instances;
        this.logs = logs;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
        this.baseBranch = baseBranch;
    }

    public static ApplicationTemplateVOBuilder builder() {
        return new ApplicationTemplateVOBuilder();
    }

    public String getBaseBranch() {
        if (baseBranch != null) {
            return baseBranch;
        } else {
            return "";
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        if (this.name != null) {
            return this.name;
        } else {
            return "";
        }
    }

    public String getDescription() {
        if (this.description != null) {
            return this.description;
        } else {
            return "";
        }
    }

    public Long getTemplateVersion() {
        return this.templateVersion;
    }

    public String getAlias() {
        if (this.alias != null) {
            return this.alias;
        } else {
            return "";
        }
    }

    public List<EnvironmentVariable> getEnvironmentVariables() {
        if (this.environmentVariables != null) {
            return this.environmentVariables;
        } else {
            return new ArrayList<>();
        }
    }

    public List<String> getVolumes() {
        if (this.volumes != null) {
            return this.volumes;
        } else {
            return new ArrayList<>();
        }
    }

    public Map<String, String> getPorts() {
        if (this.ports != null) {
            return this.ports;
        } else {
            return new HashMap<>();
        }
    }

    public List<String> getVersions() {
        if (this.versions != null) {
            return this.versions;
        } else {
            return new ArrayList<>();
        }
    }

    public List<ApplicationInstanceVO> getInstances() {
        if (this.instances != null) {
            return this.instances;
        } else {
            return new ArrayList<>();
        }
    }

    public List<LogVO> getLogs() {
        if (this.logs != null) {
            return this.logs;
        } else {
            return new ArrayList<>();
        }
    }

    public String getCreatedBy() {
        if (this.createdBy != null) {
            return this.createdBy;
        } else {
            return "";
        }
    }

    public Long getDateCreated() {
        return this.dateCreated;
    }

    public List<EnvironmentVariable> addEnvironmentVariable(final EnvironmentVariable environmentVariable) {
        if (this.environmentVariables != null) {
            this.environmentVariables.add(environmentVariable);
        }
        return environmentVariables;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ApplicationTemplateVO)) return false;
        final ApplicationTemplateVO other = (ApplicationTemplateVO) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$templateVersion = this.getTemplateVersion();
        final Object other$templateVersion = other.getTemplateVersion();
        if (this$templateVersion == null ? other$templateVersion != null : !this$templateVersion.equals(other$templateVersion))
            return false;
        final Object this$alias = this.getAlias();
        final Object other$alias = other.getAlias();
        if (this$alias == null ? other$alias != null : !this$alias.equals(other$alias)) return false;
        final Object this$environmentVariables = this.getEnvironmentVariables();
        final Object other$environmentVariables = other.getEnvironmentVariables();
        if (this$environmentVariables == null ? other$environmentVariables != null : !this$environmentVariables.equals(other$environmentVariables))
            return false;
        final Object this$volumes = this.getVolumes();
        final Object other$volumes = other.getVolumes();
        if (this$volumes == null ? other$volumes != null : !this$volumes.equals(other$volumes)) return false;
        final Object this$ports = this.getPorts();
        final Object other$ports = other.getPorts();
        if (this$ports == null ? other$ports != null : !this$ports.equals(other$ports)) return false;
        final Object this$versions = this.getVersions();
        final Object other$versions = other.getVersions();
        if (this$versions == null ? other$versions != null : !this$versions.equals(other$versions)) return false;
        final Object this$instances = this.getInstances();
        final Object other$instances = other.getInstances();
        if (this$instances == null ? other$instances != null : !this$instances.equals(other$instances)) return false;
        final Object this$logs = this.getLogs();
        final Object other$logs = other.getLogs();
        if (this$logs == null ? other$logs != null : !this$logs.equals(other$logs)) return false;
        final Object this$createdBy = this.getCreatedBy();
        final Object other$createdBy = other.getCreatedBy();
        if (this$createdBy == null ? other$createdBy != null : !this$createdBy.equals(other$createdBy)) return false;
        final Object this$dateCreated = this.getDateCreated();
        final Object other$dateCreated = other.getDateCreated();
        if (this$dateCreated == null ? other$dateCreated != null : !this$dateCreated.equals(other$dateCreated))
            return false;
        final Object this$baseBranch = this.getBaseBranch();
        final Object other$baseBranch = other.getBaseBranch();
        return this$baseBranch == null ? other$baseBranch == null : this$baseBranch.equals(other$baseBranch);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ApplicationTemplateVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $templateVersion = this.getTemplateVersion();
        result = result * PRIME + ($templateVersion == null ? 43 : $templateVersion.hashCode());
        final Object $alias = this.getAlias();
        result = result * PRIME + ($alias == null ? 43 : $alias.hashCode());
        final Object $environmentVariables = this.getEnvironmentVariables();
        result = result * PRIME + ($environmentVariables == null ? 43 : $environmentVariables.hashCode());
        final Object $volumes = this.getVolumes();
        result = result * PRIME + ($volumes == null ? 43 : $volumes.hashCode());
        final Object $ports = this.getPorts();
        result = result * PRIME + ($ports == null ? 43 : $ports.hashCode());
        final Object $versions = this.getVersions();
        result = result * PRIME + ($versions == null ? 43 : $versions.hashCode());
        final Object $instances = this.getInstances();
        result = result * PRIME + ($instances == null ? 43 : $instances.hashCode());
        final Object $logs = this.getLogs();
        result = result * PRIME + ($logs == null ? 43 : $logs.hashCode());
        final Object $createdBy = this.getCreatedBy();
        result = result * PRIME + ($createdBy == null ? 43 : $createdBy.hashCode());
        final Object $dateCreated = this.getDateCreated();
        result = result * PRIME + ($dateCreated == null ? 43 : $dateCreated.hashCode());
        final Object $baseBranch = this.getBaseBranch();
        result = result * PRIME + ($baseBranch == null ? 43 : $baseBranch.hashCode());
        return result;
    }

    public static class ApplicationTemplateVOBuilder {
        private Long id;
        private String name;
        private String description;
        private Long templateVersion;
        private String alias;
        private List<EnvironmentVariable> environmentVariables;
        private List<String> volumes;
        private Map<String, String> ports;
        private List<String> versions;
        private List<ApplicationInstanceVO> instances;
        private List<LogVO> logs;
        private String createdBy;
        private Long dateCreated;
        private String baseBranch;

        ApplicationTemplateVOBuilder() {
        }

        public ApplicationTemplateVOBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ApplicationTemplateVOBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public ApplicationTemplateVOBuilder baseBranch(final String baseBranch) {
            this.baseBranch = baseBranch;
            return this;
        }

        public ApplicationTemplateVOBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public ApplicationTemplateVOBuilder templateVersion(final Long templateVersion) {
            this.templateVersion = templateVersion;
            return this;
        }

        public ApplicationTemplateVOBuilder alias(final String alias) {
            this.alias = alias;
            return this;
        }

        public ApplicationTemplateVOBuilder environmentVariables(final List<EnvironmentVariable> environmentVariables) {
            this.environmentVariables = environmentVariables;
            return this;
        }

        public ApplicationTemplateVOBuilder volumes(final List<String> volumes) {
            this.volumes = volumes;
            return this;
        }

        public ApplicationTemplateVOBuilder ports(final Map<String, String> ports) {
            this.ports = ports;
            return this;
        }

        public ApplicationTemplateVOBuilder versions(final List<String> versions) {
            this.versions = versions;
            return this;
        }

        public ApplicationTemplateVOBuilder instances(final List<ApplicationInstanceVO> instances) {
            this.instances = instances;
            return this;
        }

        public ApplicationTemplateVOBuilder logs(final List<LogVO> logs) {
            this.logs = logs;
            return this;
        }

        public ApplicationTemplateVOBuilder createdBy(final String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public ApplicationTemplateVOBuilder dateCreated(final Long dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public ApplicationTemplateVO build() {
            return new ApplicationTemplateVO(id, name, description, templateVersion, alias, environmentVariables,
                    volumes, ports, versions, instances, logs, createdBy, dateCreated, baseBranch);
        }

        public String toString() {
            return "ApplicationTemplateVO.ApplicationTemplateVOBuilder(id=" + this.id + ", name=" + this.name
                    + ", description=" + this.description + ", lastRelease=" + this.templateVersion
                    + ", alias=" + this.alias + ", env=" + this.environmentVariables + ", volumes=" + this.volumes
                    + ", ports=" + this.ports + ", versions=" + this.versions + ", instances=" + this.instances
                    + ", logs=" + this.logs + ", createdBy=" + this.createdBy + ", dateCreated="
                    + this.dateCreated + ", baseBranch=" + this.baseBranch + ")";
        }
    }
}