package com.demo.cqrseventsourcing.projectorservice.adapters.primary;

import com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections.AchievmentsProjection;
import com.demo.cqrseventsourcing.projectorservice.application.usecases.ImportAchievment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProjectionsFactory {
    private final EventStoreConnectionFactory eventStoreConnectionFactory;
    private final ImportAchievment importAchievment;

    public ProjectionsFactory(
            @Autowired EventStoreConnectionFactory eventStoreConnectionFactory,
            @Qualifier("validationUseCase") ImportAchievment importAchievment
    ) {
        this.eventStoreConnectionFactory = eventStoreConnectionFactory;
        this.importAchievment = importAchievment;
    }

    public List<Runnable> getProjections() {
        var runnables = new ArrayList<Runnable>();
        runnables.add(new AchievmentsProjection(eventStoreConnectionFactory, importAchievment));
        return Collections.unmodifiableList(runnables);
    }
}
