package com.wine.to.up.deployment.service.dao;

import com.wine.to.up.deployment.service.entity.ApplicationInstance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationInstanceRepository extends MongoRepository<ApplicationInstance, Long>
{
    List<ApplicationInstance> findByTemplateName(String templateName);

    List<ApplicationInstance> findByTemplateId(Long templateId);
}
