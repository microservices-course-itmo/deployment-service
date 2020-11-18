package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class SettingsVO {
    private final String dockerAddress;
    private final String registry;

    public SettingsVO(String dockerAddress, String registry) {
        this.dockerAddress = dockerAddress;
        this.registry = registry;
    }
}