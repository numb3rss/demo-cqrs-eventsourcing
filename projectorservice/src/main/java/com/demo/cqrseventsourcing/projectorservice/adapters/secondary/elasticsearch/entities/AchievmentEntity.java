package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.elasticsearch.entities;

import java.util.UUID;

public class AchievmentEntity extends BaseEntity {
    private UUID id;
    private String name;

    public AchievmentEntity(UUID id, String name)
    {
        super();
        this.id = id;
        this.name = name;
    }

    public UUID getId()
    {
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
