package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wine.to.up.deployment.service.entity.EnvironmentVariable;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
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

    private ApplicationTemplateVO(Long id, String name, String description, Long templateVersion, String alias, List<EnvironmentVariable> environmentVariables, List<String> volumes, Map<String, String> ports, List<String> versions, List<ApplicationInstanceVO> instances, List<LogVO> logs, String createdBy, Long dateCreated, String baseBranch) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = 1337L;
        }
        if (name != null) {
            this.name = name;
        } else {
            this.name = "";
        }
        if (description != null) {
            this.description = description;
        } else {
            this.description = "";
        }
        if (templateVersion != null) {
            this.templateVersion = templateVersion;
        } else {
            this.templateVersion = 1337L;
        }
        if (alias != null) {
            this.alias = alias;
        } else {
            this.alias = "";
        }
        if (environmentVariables != null) {
            this.environmentVariables = environmentVariables;
        } else {
            this.environmentVariables = new ArrayList<>();
        }
        if (volumes != null) {
            this.volumes = volumes;
        } else {
            this.volumes = new ArrayList<>();
        }
        if (ports != null) {
            this.ports = ports;
        } else {
            this.ports = new HashMap<>();
        }
        if (versions != null) {
            this.versions = versions;
        } else {
            this.versions = new ArrayList<>();
        }
        if (instances != null) {
            this.instances = instances;
        } else {
            this.instances = new ArrayList<>();
        }
        if (logs != null) {
            this.logs = logs;
        } else {
            this.logs = new ArrayList<>();
        }

        if (createdBy != null) {
            this.createdBy = createdBy;
        } else {
            this.createdBy = "";
        }

        if (dateCreated != null) {
            this.dateCreated = dateCreated;
        } else {
            this.dateCreated = 1337L;
        }

        if (baseBranch != null) {
            this.baseBranch = baseBranch;
        } else {
            this.baseBranch = "";
        }
    }

    public static ApplicationTemplateVOBuilder builder() {
        return new ApplicationTemplateVOBuilder();
    }

    public String getBaseBranch() {
        return baseBranch;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getTemplateVersion() {
        return this.templateVersion;
    }

    public String getAlias() {
        return this.alias;
    }

    public List<EnvironmentVariable> getEnvironmentVariables() {
        return this.environmentVariables;
    }

    public List<String> getVolumes() {
        return this.volumes;
    }

    public Map<String, String> getPorts() {
        return this.ports;
    }

    public List<String> getVersions() {
        return this.versions;
    }

    public List<ApplicationInstanceVO> getInstances() {
        return this.instances;
    }

    public List<LogVO> getLogs() {
        return this.logs;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Long getDateCreated() {
        return this.dateCreated;
    }

    public List<EnvironmentVariable> addEnvironmentVariable(EnvironmentVariable environmentVariable) {
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

        public ApplicationTemplateVOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ApplicationTemplateVOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ApplicationTemplateVOBuilder baseBranch(String baseBranch) {
            this.baseBranch = baseBranch;
            return this;
        }

        public ApplicationTemplateVOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ApplicationTemplateVOBuilder templateVersion(Long templateVersion) {
            this.templateVersion = templateVersion;
            return this;
        }

        public ApplicationTemplateVOBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public ApplicationTemplateVOBuilder environmentVariables(List<EnvironmentVariable> environmentVariables) {
            this.environmentVariables = environmentVariables;
            return this;
        }

        public ApplicationTemplateVOBuilder volumes(List<String> volumes) {
            this.volumes = volumes;
            return this;
        }

        public ApplicationTemplateVOBuilder ports(Map<String, String> ports) {
            this.ports = ports;
            return this;
        }

        public ApplicationTemplateVOBuilder versions(List<String> versions) {
            this.versions = versions;
            return this;
        }

        public ApplicationTemplateVOBuilder instances(List<ApplicationInstanceVO> instances) {
            this.instances = instances;
            return this;
        }

        public ApplicationTemplateVOBuilder logs(List<LogVO> logs) {
            this.logs = logs;
            return this;
        }

        public ApplicationTemplateVOBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public ApplicationTemplateVOBuilder dateCreated(Long dateCreated) {
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
