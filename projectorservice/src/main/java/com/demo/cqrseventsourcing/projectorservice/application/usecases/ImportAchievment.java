package com.demo.cqrseventsourcing.projectorservice.application.usecases;

import com.demo.cqrseventsourcing.projectorservice.application.IUseCase;
import eventstore.core.Event;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service
public class ImportAchievment implements IUseCase<Event> {
    @Override
    public Option handle(Event event) {

        return Option.of(null);
    }
}
