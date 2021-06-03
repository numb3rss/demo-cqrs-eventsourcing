package com.demo.cqrseventsourcing.projectorservice.domain;

import java.util.UUID;

public class Achievment {
    private UUID id;
    private String name;

    public UUID getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
}
