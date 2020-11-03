package com.wine.to.up.deployment.service.dao;

import com.wine.to.up.deployment.service.entity.ApplicationInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ApplicationInstanceRepository extends MongoRepository<ApplicationInstance, Long>
{
    List<ApplicationInstance> findByTemplateId(Long templateId);
}
