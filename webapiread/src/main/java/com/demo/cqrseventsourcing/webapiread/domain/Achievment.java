package com.demo.cqrseventsourcing.webapiread.domain;

import java.util.UUID;

public class Achievment
{
    private UUID id;
    private String name;

    public Achievment(UUID id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
}
