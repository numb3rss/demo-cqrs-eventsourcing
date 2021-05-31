package com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections;

import com.demo.cqrseventsourcing.projectorservice.application.usecases.ImportAchievment;
import eventstore.core.IndexedEvent;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AchievmentProjection implements IProjection{
    private final ImportAchievment importAchievment;

    public AchievmentProjection(
            @Qualifier("validationUseCase")ImportAchievment importAchievment)
    {
        this.importAchievment = importAchievment;
    }

    public Option project(IndexedEvent event) {
        switch(event.event().data().eventType()) {
            case "create-achievment-event":
                System.out.println(event);
                this.importAchievment.handle(event);
                return Option.of("OK");
            default:
                return null;
        }
    }
}
