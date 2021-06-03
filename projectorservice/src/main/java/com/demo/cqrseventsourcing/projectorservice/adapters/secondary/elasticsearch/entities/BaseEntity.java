package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.elasticsearch.entities;

import java.sql.Timestamp;

public abstract class BaseEntity {
    protected Timestamp timeStamp;

    public BaseEntity()
    {
        timeStamp = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTimeStamp()
    {
        return this.timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp)
    {
        this.timeStamp = timeStamp;
    }
}
