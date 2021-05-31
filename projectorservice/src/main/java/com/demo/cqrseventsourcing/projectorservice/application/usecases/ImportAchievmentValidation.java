package com.demo.cqrseventsourcing.projectorservice.application.usecases;

import eventstore.core.Event;
import eventstore.core.IndexedEvent;
import io.vavr.control.Option;
import org.springframework.stereotype.Component;

@Component
public class ImportAchievmentValidation extends ImportAchievment {
    private final ImportAchievment importAchievment;

    public ImportAchievmentValidation(ImportAchievment importAchievment){
        this.importAchievment = importAchievment;
    }

    @Override
    public Option handle(IndexedEvent event) {
        var eventId = event.event().streamId().value();
        var eventData = event.event().data();
        if(!eventId.equals("achievments") || !eventData.eventType().equals("create-achievment-event"))
            return Option.of("L'évènement ne correspond pas au cas d'utilisation");
        return this.importAchievment.handle(event);
    }
}
