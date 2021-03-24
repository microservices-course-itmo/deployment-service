package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wine.to.up.deployment.service.entity.EnvironmentVariable;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode
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
        this.environmentVariables.add(environmentVariable);
        return environmentVariables;
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