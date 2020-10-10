package com.wine.to.up.deployment.service.service.impl;

import com.wine.to.up.deployment.service.command.provider.ImageCommandProvider;
import com.wine.to.up.deployment.service.dto.ImageDto;
import com.wine.to.up.deployment.service.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeploymentServiceImpl implements DeploymentService {

    private List<ImageCommandProvider> imageCommandProviderList;

    @Autowired
    public void setImageCommandProviderList(final List<ImageCommandProvider> imageCommandProviderList) {
        this.imageCommandProviderList = imageCommandProviderList;
    }

    @Override
    public ImageDto processImageRequest(final ImageDto imageDto) {
        final var provider = imageCommandProviderList.stream()
                .filter(it -> it.accepts(imageDto))
                .findFirst()
                .orElseThrow();
        return provider.process(imageDto);
    }
}
