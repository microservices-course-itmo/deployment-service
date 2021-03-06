package com.wine.to.up.deployment.service.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {

    private final Boolean isTestInstance;
    private final Boolean isStopTraffic;

    @JsonCreator
    public Attributes(@JsonProperty("testInstance") final Boolean isTestInstance,
                      @JsonProperty("stopTraffic") final Boolean isStopTraffic) {
        this.isTestInstance = isTestInstance;
        this.isStopTraffic = isStopTraffic;
    }

    public boolean isTestInstance() {
        if(isTestInstance == null) {
            return false;
        }
        return isTestInstance;
    }

    public boolean isStopTraffic() {
        if(isStopTraffic == null) {
            return false;
        }
        return isStopTraffic;
    }

}
