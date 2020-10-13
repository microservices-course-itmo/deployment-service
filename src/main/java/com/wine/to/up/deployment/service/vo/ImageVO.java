package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderClassName = "Builder")
public class ImageVO {
    @JsonIgnore
    private final String id;
    @JsonIgnore
    private final String mode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String ports;
    private final String name;
    private final String image;
    private final String replicas;
}
