package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SettingsVO {
    private final String dockerAddress;
    private final String versionRegistry;
    private final String imageRegistry;

    @JsonCreator
    private SettingsVO(@JsonProperty("dockerAddress") final String dockerAddress,
                       @JsonProperty("versionRegistry") final String versionRegistry,
                       @JsonProperty("imageRegistry") final String imageRegistry) {
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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SettingsVO)) return false;
        final SettingsVO other = (SettingsVO) o;
        if (!other.canEqual(this)) return false;
        final Object this$dockerAddress = this.getDockerAddress();
        final Object other$dockerAddress = other.getDockerAddress();
        if (this$dockerAddress == null ? other$dockerAddress != null : !this$dockerAddress.equals(other$dockerAddress))
            return false;
        final Object this$versionRegistry = this.getVersionRegistry();
        final Object other$versionRegistry = other.getVersionRegistry();
        if (this$versionRegistry == null ? other$versionRegistry != null : !this$versionRegistry.equals(other$versionRegistry))
            return false;
        final Object this$imageRegistry = this.getImageRegistry();
        final Object other$imageRegistry = other.getImageRegistry();
        return this$imageRegistry == null ? other$imageRegistry == null : this$imageRegistry.equals(other$imageRegistry);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SettingsVO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $dockerAddress = this.getDockerAddress();
        result = result * PRIME + ($dockerAddress == null ? 43 : $dockerAddress.hashCode());
        final Object $versionRegistry = this.getVersionRegistry();
        result = result * PRIME + ($versionRegistry == null ? 43 : $versionRegistry.hashCode());
        final Object $imageRegistry = this.getImageRegistry();
        result = result * PRIME + ($imageRegistry == null ? 43 : $imageRegistry.hashCode());
        return result;
    }

    public static class SettingsVOBuilder {
        private String dockerAddress;
        private String versionRegistry;
        private String imageRegistry;

        SettingsVOBuilder() {
        }

        public SettingsVOBuilder dockerAddress(final String dockerAddress) {
            this.dockerAddress = dockerAddress;
            return this;
        }

        public SettingsVOBuilder versionRegistry(final String versionregistry) {
            this.versionRegistry = versionregistry;
            return this;
        }

        public SettingsVOBuilder imageRegistry(final String imageRegistry) {
            this.imageRegistry = imageRegistry;
            return this;
        }

        public SettingsVO build() {
            return new SettingsVO(dockerAddress, versionRegistry, imageRegistry);
        }

    }
}