package com.demo.cqrseventsourcing.projectorservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = "com.demo.cqrseventsourcing.projectorservice", basePackageClasses = { ThreadPoolTaskSchedulerProjections.class })
public class ThreadPoolTaskSchedulerConfig {

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        return threadPoolTaskScheduler;
    }

    @Bean
    public CronTrigger cronTrigger() {
        return new CronTrigger("10 * * * * ?");
    }

    @Bean
    public PeriodicTrigger periodicTrigger() {
        return new PeriodicTrigger(2000, TimeUnit.MICROSECONDS);
    }

    @Bean
    public PeriodicTrigger periodicFixedDelayTrigger() {
        PeriodicTrigger periodicTrigger = new PeriodicTrigger(2000, TimeUnit.MICROSECONDS);
        periodicTrigger.setFixedRate(true);
        periodicTrigger.setInitialDelay(1000);
        return periodicTrigger;
    }
}
