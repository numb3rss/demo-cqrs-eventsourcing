package com.demo.cqrseventsourcing.webapiread.adapters.primary.usecases.retrieveachievment.model;

import java.util.UUID;

public class RetrieveByIdResponse
{
    private final UUID id;
    private final String name;

    public RetrieveByIdResponse(UUID id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
