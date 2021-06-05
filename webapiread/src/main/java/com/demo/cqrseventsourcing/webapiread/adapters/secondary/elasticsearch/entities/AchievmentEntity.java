package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class AchievmentEntity {
    private UUID id;
    private String name;
    private Date timeStamp;

    @JsonProperty("id")
    public UUID getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(UUID id) {

        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {

        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("timeStamp")
    public Date getTimeStamp() {
        return timeStamp;
    }

    @JsonProperty("timeStamp")
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}