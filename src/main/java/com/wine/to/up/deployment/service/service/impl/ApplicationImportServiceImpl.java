package com.wine.to.up.deployment.service.service.impl;

import com.github.dockerjava.api.model.Service;
import com.wine.to.up.deployment.service.dao.ApplicationInstanceRepository;
import com.wine.to.up.deployment.service.dao.ApplicationTemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.service.ApplicationImportService;
import com.wine.to.up.deployment.service.service.ApplicationInstanceService;
import com.wine.to.up.deployment.service.service.SettingsService;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ApplicationImportServiceImpl implements ApplicationImportService {
    private final ApplicationTemplateRepository applicationTemplateRepository;
    private final ApplicationInstanceService applicationInstanceService;
    private final SettingsService settingsService;
    private final ApplicationInstanceRepository applicationInstanceRepository;

    public ApplicationImportServiceImpl(ApplicationInstanceService applicationInstanceService, ApplicationTemplateRepository applicationTemplateRepository, SettingsService settingsService, ApplicationInstanceRepository applicationInstanceRepository) {
        this.applicationInstanceService = applicationInstanceService;
        this.applicationTemplateRepository = applicationTemplateRepository;
        this.settingsService = settingsService;
        this.applicationInstanceRepository = applicationInstanceRepository;
    }

    private List<Service> getInstances() {
        return applicationInstanceService.getInstances();
    }

    @Override
    public List<String> importInstances() {
        List<Service> instances = getInstances();
        List<String> list = new ArrayList<>();
        for (Service instance : instances) {
            String name = instance.getSpec().getName();


            /*if (name.indexOf('_') != -1) {
                Integer amount = applicationTemplateRepository.countByName(name.substring(0, name.indexOf('_')));
                if (amount == 0)
                    list.add(name.substring(0, name.indexOf('_')));
            }

            Integer amount = applicationTemplateRepository.countByName(name);
            if (amount != 0)
                list.add(name);
        */
            if (isMicroService(instance.getSpec().getTaskTemplate().getContainerSpec().getImage())) {

                if (isApplicationInstanceExists(name)) {
                    //if (isApplicationInstanceExists(name)) {
                        list.add(name);
                    System.out.println(name);
                    //}
                }
            }
        }
        return list;
    }

    private boolean isMicroService(String image) {
        if (image.indexOf('/') != -1) {
            String address = image.substring(0, image.indexOf('/'));
            return address.equals("registry:5000") || address.equals("0.0.0.0:25001") || address.equals(settingsService.getSettings().getImageRegistry());
        }
        return false;
    }

    private boolean isApplicationTemplateExists(String name) {
        return applicationTemplateRepository.countByName(name) > 0;
    }

    private boolean isApplicationInstanceExists(String appId) {
        return applicationInstanceRepository.countByAppId(appId) > 0;
    }

    private void createApplicationTemplate(Service instance) {

    }

    private void createApplicationInstance(Service instance) {

    }
}
