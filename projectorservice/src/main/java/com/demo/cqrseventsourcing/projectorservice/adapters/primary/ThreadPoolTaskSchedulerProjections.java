package com.demo.cqrseventsourcing.projectorservice.adapters.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ThreadPoolTaskSchedulerProjections {
    private final EventStoreConnectionFactory eventStoreConnectionFactory;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final ProjectionsFactory projectionsFactory;

    public ThreadPoolTaskSchedulerProjections(
            @Autowired EventStoreConnectionFactory eventStoreConnectionFactory,
            @Autowired ThreadPoolTaskScheduler taskScheduler,
            @Autowired ProjectionsFactory projectionsFactory) {
        this.eventStoreConnectionFactory = eventStoreConnectionFactory;
        this.taskScheduler = taskScheduler;
        this.projectionsFactory = projectionsFactory;
    }

    @PostConstruct
    public void scheduleRunner() {
        var projections = projectionsFactory.getProjections();
        for (Runnable projection: projections) {
            taskScheduler.scheduleWithFixedDelay(projection, 1000);
        }
    }
}
