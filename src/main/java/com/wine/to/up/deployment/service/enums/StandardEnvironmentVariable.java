package com.wine.to.up.deployment.service.enums;

import com.wine.to.up.deployment.service.entity.EnvironmentVariable;

public enum StandardEnvironmentVariable {

    POSTGRES_HOST(new EnvironmentVariable("S_POSTGRES_HOST", "postgres")),
    MONGO_HOST(new EnvironmentVariable("S_MONGO_HOST", "mongo")),
    KAFKA_HOST(new EnvironmentVariable("S_KAFKA_BOOTSTRAP_HOST", "kafka:9092")),
    LOGSTASH_HOST(new EnvironmentVariable("S_LOGSTASH_HOST", "logstash:4560"));

    StandardEnvironmentVariable(EnvironmentVariable environmentVariable) {
        this.environmentVariable = environmentVariable;
    }

    EnvironmentVariable environmentVariable;

    public EnvironmentVariable getEnvironmentVariable() {
        return environmentVariable;
    }
}
