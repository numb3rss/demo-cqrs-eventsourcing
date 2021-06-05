package com.demo.cqrseventsourcing.webapiread.application.usecases.retrieveachievment;

import com.demo.cqrseventsourcing.cqrslibrary.IQueryResult;

import java.util.UUID;

public class RetrieveAchievmentQueryResult implements IQueryResult
{
    private final UUID id;
    private final String name;

    public RetrieveAchievmentQueryResult(UUID id, String name)
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
