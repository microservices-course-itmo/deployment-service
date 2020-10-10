package com.wine.to.up.deployment.service.command.provider;

import com.wine.to.up.deployment.service.dto.ImageDto;


public interface ImageCommandProvider {
    /**
     * Specifies wether this provider can process given request
     *
     * @param imageRequestDto request
     * @return true if can process, false otherwise
     */
    boolean accepts(ImageDto imageRequestDto);

    ImageDto process(ImageDto imageDto);
}
