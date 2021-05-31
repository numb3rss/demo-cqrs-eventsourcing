package com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections;

import com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore.EventStoreConnectionFactory;
import com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore.CheckpointStore;
import eventstore.akka.SubscriptionObserver;
import eventstore.core.*;
import eventstore.j.EsConnection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Closeable;
import java.util.List;

public class ProjectionHandler implements Runnable {
    private final EsConnection connection;
    private final CheckpointStore checkpointStore;
    private final List<IProjection> projections;

    public ProjectionHandler(
            @Autowired EventStoreConnectionFactory eventStoreConnectionFactory,
            @Autowired CheckpointStore checkpointStore,
            @Autowired ProjectionFactory projectionFactory
    ) {
        connection = eventStoreConnectionFactory.getConnection();
        this.checkpointStore = checkpointStore;
        projections = projectionFactory.getProjections();
    }

    @Override
    public void run() {
        var checkpoint = checkpointStore.getCheckpoint();
        connection.subscribeToAllFrom(new Observer(), checkpoint, true, null);
    }

    private class Observer implements SubscriptionObserver<IndexedEvent> {

        @Override
        public void onLiveProcessingStart(Closeable subscription) {

        }

        @Override
        public void onEvent(IndexedEvent event, Closeable subscription) {
            if(!(event.event() instanceof ResolvedEvent))
                return;
            for (IProjection projection : projections)
            {
                var result = projection.project(event);
                if(result != null)
                    checkpointStore.storeCheckpoint(event.position());
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onClose() {

        }
    }
}
