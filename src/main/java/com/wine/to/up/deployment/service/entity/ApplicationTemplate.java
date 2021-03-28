package com.wine.to.up.deployment.service.entity;

import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@ConstructorBinding
@Document(collection = "templates")
public class ApplicationTemplate {

    @Transient
    public static final String SEQUENCE_NAME = "app_template_sequence";

    @Id
    private Long id;
    private Long templateVersion;
    private String createdBy;
    private String name;
    private String description;
    private Map<String, String> portMappings;
    private List<String> volumes;
    private List<EnvironmentVariable> environmentVariables;
    private String baseBranch;
    private Long dateCreated;
    private Long memoryLimits = 3000000000L;

    public ApplicationTemplate(Long templateVersion, String createdBy, String name, Map<String, String> portMappings,
                               List<String> volumes, List<EnvironmentVariable> environmentVariables, String description,
                               String baseBranch) {
        this.templateVersion = templateVersion;
        this.createdBy = createdBy;
        this.name = name;
        this.portMappings = portMappings;
        this.volumes = volumes;
        this.environmentVariables = environmentVariables;
        this.description = description;
        this.dateCreated = System.currentTimeMillis();
        this.baseBranch = baseBranch;
    }

    public ApplicationTemplate() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getTemplateVersion() {
        return this.templateVersion;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, String> getPortMappings() {
        return this.portMappings;
    }

    public List<String> getVolumes() {
        return this.volumes;
    }

    public List<EnvironmentVariable> getEnvironmentVariables() {
        return this.environmentVariables;
    }

    public Long getDateCreated() {
        return this.dateCreated;
    }

    public Long getMemoryLimits() {
        return this.memoryLimits;
    }

    public String getBaseBranch() {
        return baseBranch;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemplateVersion(Long templateVersion) {
        this.templateVersion = templateVersion;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPortMappings(Map<String, String> portMappings) {
        this.portMappings = portMappings;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }

    public void setEnvironmentVariables(List<EnvironmentVariable> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public void setBaseBranch(String baseBranch) {
        this.baseBranch = baseBranch;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setMemoryLimits(Long memoryLimits) {
        this.memoryLimits = memoryLimits;
    }
}
