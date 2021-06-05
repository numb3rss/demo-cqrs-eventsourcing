package com.demo.cqrseventsourcing.webapiread.application.usecases.retrieveachievment;

import com.demo.cqrseventsourcing.cqrslibrary.IQuery;

import java.util.UUID;

public class RetrieveAchievmentQuery implements IQuery
{
    private UUID id;

    public RetrieveAchievmentQuery(UUID id)
    {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
