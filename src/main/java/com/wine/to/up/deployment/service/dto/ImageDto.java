package com.wine.to.up.deployment.service.dto;

import com.wine.to.up.deployment.service.enums.ImageRequestType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Builder
@Getter
public class ImageDto {
    private final ImageRequestType imageRequestType;

    private final Map<String, Object> payload;
}
