package com.wine.to.up.deployment.service.service;

import com.wine.to.up.deployment.service.dto.ImageDto;


public interface DeploymentService {
    ImageDto processImageRequest(final ImageDto imageDto);
}
