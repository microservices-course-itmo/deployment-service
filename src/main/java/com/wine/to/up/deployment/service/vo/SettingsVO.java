package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingsVO {
    private final String dockerAddress;
    private final String versionRegistry;
    private final String imageRegistry;

    private SettingsVO(String dockerAddress, String versionRegistry, final String imageRegistry) {
        this.dockerAddress = dockerAddress;
        this.versionRegistry = versionRegistry;
        this.imageRegistry = imageRegistry;
    }

    public static SettingsVOBuilder builder() {
        return new SettingsVOBuilder();
    }

    public String getDockerAddress() {
        return this.dockerAddress;
    }

    public String getVersionRegistry() {
        return this.versionRegistry;
    }

    public String getImageRegistry() {
        return imageRegistry;
    }

    public static class SettingsVOBuilder {
        private String dockerAddress;
        private String versionRegistry;
        private String imageRegistry;

        SettingsVOBuilder() {
        }

        public SettingsVOBuilder dockerAddress(String dockerAddress) {
            this.dockerAddress = dockerAddress;
            return this;
        }

        public SettingsVOBuilder versionRegistry(String versionregistry) {
            this.versionRegistry = versionregistry;
            return this;
        }

        public SettingsVOBuilder imageRegistry(String imageRegistry) {
            this.imageRegistry = imageRegistry;
            return this;
        }

        public SettingsVO build() {
            return new SettingsVO(dockerAddress, versionRegistry, imageRegistry);
        }

    }
}