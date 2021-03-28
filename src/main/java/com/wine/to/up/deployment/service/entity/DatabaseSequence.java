package com.wine.to.up.deployment.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;
    private Long seq;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return this.seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}