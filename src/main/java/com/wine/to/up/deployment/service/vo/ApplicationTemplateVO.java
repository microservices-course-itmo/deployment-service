package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wine.to.up.deployment.service.entity.Environment;
import com.wine.to.up.deployment.service.entity.Log;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
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
    private final Date dateCreated;

}
