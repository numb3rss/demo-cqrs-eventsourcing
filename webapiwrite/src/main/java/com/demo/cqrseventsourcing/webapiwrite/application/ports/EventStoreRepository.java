package com.demo.cqrseventsourcing.webapiwrite.application.ports;

public interface EventStoreRepository<TAggregate>
{
    void emit(TAggregate aggregate);
}
