package com.wine.to.up.deployment.service.DAO;

import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepository extends MongoRepository<ApplicationTemplate, String> { }
