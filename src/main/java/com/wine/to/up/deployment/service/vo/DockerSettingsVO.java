package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DockerSettingsVO {
    private final String id;
    private final String dockerAddress;
    private final String registry;

    public DockerSettingsVO(String id, String dockerAddress, String registry) {
        this.id = id;
        this.dockerAddress = dockerAddress;
        this.registry = registry;
    }

    public String getId(){
        return id;
    }
    public String getDockerAddress() {
        return dockerAddress;
    }

    public String getRegistry() {
        return registry;
    }

    public static DockerSettingsVOBuilder builder() {
        return new DockerSettingsVOBuilder();
    }


    public static class DockerSettingsVOBuilder {
        private String id;
        private String dockerAddress;
        private String registry;

        DockerSettingsVOBuilder() {
        }

        public DockerSettingsVOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public DockerSettingsVOBuilder dockerAddress(String dockerAddress) {
            this.dockerAddress = dockerAddress;
            return this;
        }

        public DockerSettingsVOBuilder registry(String registry) {
            this.registry = registry;
            return this;
        }

        public DockerSettingsVO build() {
            return new DockerSettingsVO(id, dockerAddress, registry);
        }

        public String toString() {
            return "DockerSettingsVO.DockerSettingsVOBuilder(id=" + this.id + ", dockerAddress=" + this.dockerAddress + ", registry=" + this.registry + ")";
        }
    }
}
