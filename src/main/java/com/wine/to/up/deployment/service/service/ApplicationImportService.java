package com.wine.to.up.deployment.service.service;

import com.github.dockerjava.api.model.Service;

import java.util.List;

public interface ApplicationImportService {
    List<String> importInstances();

}
