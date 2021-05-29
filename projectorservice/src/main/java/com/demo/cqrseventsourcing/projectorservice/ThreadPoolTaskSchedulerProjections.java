package com.demo.cqrseventsourcing.projectorservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ThreadPoolTaskSchedulerProjections {
    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @PostConstruct
    public void scheduleRunner() {
        taskScheduler.scheduleWithFixedDelay(new AchievmentsRunnable(), 1000);
    }
}
