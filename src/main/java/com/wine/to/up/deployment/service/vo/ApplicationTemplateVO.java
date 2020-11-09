package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wine.to.up.deployment.service.entity.Environment;
import com.wine.to.up.deployment.service.entity.Log;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationTemplateVO {
    private final Long id;
    private final String name;
    private final String description;
    private final String lastRelease;
    private final String alias;
    private final List<Environment> env;
    private final List<String> volumes;
    private final List<String> ports;
    private final List<String> versions;
    private final List<ApplicationInstanceVO> instances;
    private final List<Log> logs;
    private final String createdBy;
    private final LocalDateTime dateCreated;

    ApplicationTemplateVO(Long id, String name, String description, String lastRelease, String alias, List<Environment> env, List<String> volumes, List<String> ports, List<String> versions, List<ApplicationInstanceVO> instances, List<Log> logs, String createdBy, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lastRelease = lastRelease;
        this.alias = alias;
        this.env = env;
        this.volumes = volumes;
        this.ports = ports;
        this.versions = versions;
        this.instances = instances;
        this.logs = logs;
        this.createdBy = createdBy;
        this.dateCreated = dateCreated;
    }

    public static ApplicationTemplateVOBuilder builder() {
        return new ApplicationTemplateVOBuilder();
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

    public String getLastRelease() {
        return this.lastRelease;
    }

    public String getAlias() {
        return this.alias;
    }

    public List<Environment> getEnv() {
        return this.env;
    }

    public List<String> getVolumes() {
        return this.volumes;
    }

    public List<String> getPorts() {
        return this.ports;
    }

    public List<String> getVersions() {
        return this.versions;
    }

    public List<ApplicationInstanceVO> getInstances() {
        return this.instances;
    }

    public List<Log> getLogs() {
        return this.logs;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    public static class ApplicationTemplateVOBuilder {
        private Long id;
        private String name;
        private String description;
        private String lastRelease;
        private String alias;
        private List<Environment> env;
        private List<String> volumes;
        private List<String> ports;
        private List<String> versions;
        private List<ApplicationInstanceVO> instances;
        private List<Log> logs;
        private String createdBy;
        private LocalDateTime dateCreated;

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

        public ApplicationTemplateVOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ApplicationTemplateVOBuilder lastRelease(String lastRelease) {
            this.lastRelease = lastRelease;
            return this;
        }

        public ApplicationTemplateVOBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public ApplicationTemplateVOBuilder env(List<Environment> env) {
            this.env = env;
            return this;
        }

        public ApplicationTemplateVOBuilder volumes(List<String> volumes) {
            this.volumes = volumes;
            return this;
        }

        public ApplicationTemplateVOBuilder ports(List<String> ports) {
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

        public ApplicationTemplateVOBuilder logs(List<Log> logs) {
            this.logs = logs;
            return this;
        }

        public ApplicationTemplateVOBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public ApplicationTemplateVOBuilder dateCreated(LocalDateTime dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public ApplicationTemplateVO build() {
            return new ApplicationTemplateVO(id, name, description, lastRelease, alias, env, volumes, ports, versions, instances, logs, createdBy, dateCreated);
        }

        public String toString() {
            return "ApplicationTemplateVO.ApplicationTemplateVOBuilder(id=" + this.id + ", name=" + this.name + ", description=" + this.description + ", lastRelease=" + this.lastRelease + ", alias=" + this.alias + ", env=" + this.env + ", volumes=" + this.volumes + ", ports=" + this.ports + ", versions=" + this.versions + ", instances=" + this.instances + ", logs=" + this.logs + ", createdBy=" + this.createdBy + ", dateCreated=" + this.dateCreated + ")";
        }
    }
}
