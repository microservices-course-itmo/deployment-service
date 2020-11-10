package com.wine.to.up.deployment.service.dao;

import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationTemplateRepository extends MongoRepository<ApplicationTemplate, Long> {
    Optional<ApplicationTemplate> findFirstByNameOrderByIdDesc(String name);

    Integer countByName(String name);
}
