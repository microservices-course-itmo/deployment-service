package com.wine.to.up.deployment.service.vo;


import com.wine.to.up.deployment.service.enums.ApplicationInstanceStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationInstanceVO   {

    private final String id;

    private final String appId;

    private final  int templateId;

    private final String version;

    private final String containerId;

    private final  String dateCreated;

    private final String createdBy;

    private final String alias ;

    public final ApplicationInstanceStatus status;
}
