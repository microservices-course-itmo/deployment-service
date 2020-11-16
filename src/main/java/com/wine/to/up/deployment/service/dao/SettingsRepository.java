package com.wine.to.up.deployment.service.dao;

import com.github.dockerjava.api.DockerClient;
import com.wine.to.up.deployment.service.entity.Settings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SettingsRepository extends MongoRepository<Settings, Long> {
    DockerClient getByDockerAddress(String dockerAddress);
}
