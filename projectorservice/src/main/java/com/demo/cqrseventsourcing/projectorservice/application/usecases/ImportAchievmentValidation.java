package com.demo.cqrseventsourcing.projectorservice.application.usecases;

import com.demo.cqrseventsourcing.projectorservice.application.ports.ElasticSearchRepository;
import eventstore.core.IndexedEvent;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportAchievmentValidation extends ImportAchievment {
    private final ImportAchievment importAchievment;

    public ImportAchievmentValidation(ImportAchievment importAchievment, @Autowired ElasticSearchRepository elasticSearchRepository){
        super(elasticSearchRepository);
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
