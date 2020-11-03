package com.wine.to.up.deployment.service.vo;


import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationInstanceVO   {

    private final Long id;

    private final String appId;

    private final Long templateId;

    private final String version;

    private final String containerId;

    private final  String dateCreated;

    private final String createdBy;

    private final String alias;

    private final String url;

    private final ApplicationInstanceStatus status;
}
