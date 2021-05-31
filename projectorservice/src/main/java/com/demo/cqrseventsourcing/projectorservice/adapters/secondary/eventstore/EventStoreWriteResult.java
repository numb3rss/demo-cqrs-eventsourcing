package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore;

import akka.actor.AbstractActor;
import akka.actor.Status;
import eventstore.core.WriteEventsCompleted;

public class EventStoreWriteResult extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(WriteEventsCompleted.class, m -> {
                })
                .match(Status.Failure.class, f -> {
                })
                .build();
    }
}
