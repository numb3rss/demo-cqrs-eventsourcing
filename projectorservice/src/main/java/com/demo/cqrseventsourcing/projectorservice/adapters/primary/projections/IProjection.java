package com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections;

import eventstore.core.IndexedEvent;
import io.vavr.control.Option;

public interface IProjection {
    Option project(IndexedEvent indexedEvent);
}
