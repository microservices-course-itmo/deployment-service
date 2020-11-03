package com.wine.to.up.deployment.service.dao;

import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationTemplateRepository extends MongoRepository<ApplicationTemplate, Long> {
}
