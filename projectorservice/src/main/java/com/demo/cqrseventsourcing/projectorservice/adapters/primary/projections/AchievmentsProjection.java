package com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections;

import com.demo.cqrseventsourcing.projectorservice.adapters.primary.EventStoreConnectionFactory;
import com.demo.cqrseventsourcing.projectorservice.application.usecases.ImportAchievment;
import eventstore.akka.SubscriptionObserver;
import eventstore.core.Event;
import eventstore.j.EsConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.Closeable;

public class AchievmentsProjection implements Runnable {
    private final EsConnection connection;
    private final ImportAchievment importAchievment;

    public AchievmentsProjection(
            @Autowired EventStoreConnectionFactory eventStoreConnectionFactory,
            @Qualifier("validationUseCase") ImportAchievment importAchievment
    ) {
        connection = eventStoreConnectionFactory.getConnection();
        this.importAchievment = importAchievment;
    }

    @Override
    public void run() {
        connection.subscribeToStream("achievments", new SubscriptionObserver<Event>() {
            @Override
            public void onLiveProcessingStart(Closeable subscription) {
            }

            @Override
            public void onEvent(Event event, Closeable subscription) {
                System.out.println(event);
                importAchievment.handle(event);
                event.record();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onClose() {

            }
        }, true, null);
    }
}
