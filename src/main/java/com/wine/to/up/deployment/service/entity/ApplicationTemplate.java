package com.wine.to.up.deployment.service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String templateVersion;
    private String createdBy;
    private String name;
    private List<String> portMappings;
    private List<String> volumes;
    private List<Environment> env;
    private long memoryLimits = 3000000000L;

}
