package com.wine.to.up.deployment.service.dao;

import com.wine.to.up.deployment.service.entity.Log;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends MongoRepository<Log, Long> {
    List<Log> findAllByTemplateName(String templateName);
}
