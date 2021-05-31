package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore;

public class Checkpoint {
    private long commitPosition;
    private long preparePosition;

    public Checkpoint()
    {

    }

    public Checkpoint(long commitPosition, long preparePosition) {
        this.commitPosition = commitPosition;
        this.preparePosition = preparePosition;
    }

    public long getCommitPosition()
    {
        return this.commitPosition;
    }

    public long getPreparePosition()
    {
        return this.preparePosition;
    }
}
