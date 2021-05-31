package com.demo.cqrseventsourcing.projectorservice.application.usecases;

import com.demo.cqrseventsourcing.projectorservice.application.IUseCase;
import eventstore.core.Event;
import eventstore.core.IndexedEvent;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class ImportAchievment implements IUseCase<IndexedEvent> {
    @Override
    public Option handle(IndexedEvent event) {

        return Option.of(null);
    }
}
