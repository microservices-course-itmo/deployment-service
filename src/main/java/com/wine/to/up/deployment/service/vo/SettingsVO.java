package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingsVO {
    private final String dockerAddress;
    private final String registry;

    public SettingsVO(String dockerAddress, String registry) {
        this.dockerAddress = dockerAddress;
        this.registry = registry;
    }

    public static SettingsVOBuilder builder() {
        return new SettingsVOBuilder();
    }

    public String getDockerAddress() {
        return this.dockerAddress;
    }

    public String getRegistry() {
        return this.registry;
    }

    public static class SettingsVOBuilder {
        private String dockerAddress;
        private String registry;

        SettingsVOBuilder() {
        }

        public SettingsVOBuilder dockerAddress(String dockerAddress) {
            this.dockerAddress = dockerAddress;
            return this;
        }

        public SettingsVOBuilder registry(String registry) {
            this.registry = registry;
            return this;
        }

        public SettingsVO build() {
            return new SettingsVO(dockerAddress, registry);
        }

        public String toString() {
            return "SettingsVO.SettingsVOBuilder(dockerAddress=" + this.dockerAddress + ", registry=" + this.registry + ")";
        }
    }
}