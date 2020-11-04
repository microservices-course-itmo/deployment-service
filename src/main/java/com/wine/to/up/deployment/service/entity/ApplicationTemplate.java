package com.wine.to.up.deployment.service.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ConstructorBinding
@NoArgsConstructor
@Document(collection = "templates")
public class ApplicationTemplate {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Long id;
    private List<String> templateVersions;
    private String createdBy;
    private String name;
    private String description;
    private List<String> portMappings;
    private List<String> volumes;
    private List<Environment> environmentVariables;
    private LocalDateTime dateCreated;
    private long memoryLimits = 3000000000L;

    public ApplicationTemplate(List<String> templateVersions, String createdBy, String name, List<String> portMappings, List<String> volumes, List<Environment> environmentVariables, String description) {
        this.templateVersions = templateVersions;
        this.createdBy = createdBy;
        this.name = name;
        this.portMappings = portMappings;
        this.volumes = volumes;
        this.environmentVariables = environmentVariables;
        this.description = description;
        this.dateCreated = LocalDateTime.now();
    }
}
