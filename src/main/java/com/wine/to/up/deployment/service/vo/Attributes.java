package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class Attributes {

    private final Boolean isTestInstance;
    private final Boolean isStopTraffic;

    @JsonCreator
    public Attributes(@JsonProperty("testInstance") final Boolean isTestInstance, Boolean isStopTraffic) {
        this.isTestInstance = isTestInstance;
        this.isStopTraffic = isStopTraffic;
    }

    public Boolean isTestInstance() {
        if(isTestInstance == null) {
            return false;
        }
        return isTestInstance;
    }

    public Boolean isStopTraffic() {
        if(isStopTraffic == null) {
            return false;
        }
        return isStopTraffic;
    }

}
