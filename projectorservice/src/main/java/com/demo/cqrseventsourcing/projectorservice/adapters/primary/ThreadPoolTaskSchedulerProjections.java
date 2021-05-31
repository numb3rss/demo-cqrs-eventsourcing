package com.demo.cqrseventsourcing.projectorservice.adapters.primary;

import com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections.ProjectionFactory;
import com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections.ProjectionHandler;
import com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore.CheckpointStore;
import com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore.EventStoreConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class ThreadPoolTaskSchedulerProjections {
    private final EventStoreConnectionFactory eventStoreConnectionFactory;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final CheckpointStore checkpointStore;
    private final ProjectionFactory projectionFactory;

    public ThreadPoolTaskSchedulerProjections(
            @Autowired EventStoreConnectionFactory eventStoreConnectionFactory,
            @Autowired ThreadPoolTaskScheduler taskScheduler,
            @Autowired CheckpointStore checkpointStore,
            @Autowired ProjectionFactory projectionFactory) {
        this.eventStoreConnectionFactory = eventStoreConnectionFactory;
        this.taskScheduler = taskScheduler;
        this.checkpointStore = checkpointStore;
        this.projectionFactory = projectionFactory;
    }

    @PostConstruct
    public void scheduleRunner()
    {
        taskScheduler.schedule(new ProjectionHandler(eventStoreConnectionFactory, checkpointStore, projectionFactory), new Date(System.currentTimeMillis()));
    }
}
