package com.demo.cqrseventsourcing.projectorservice;

import sun.misc.Signal;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(new AchievmentsRunnable(), 5, 1, TimeUnit.SECONDS);

        Signal signal = new Signal("INT");

        Signal.handle(signal, sig -> {
            System.out.println("Signal handle : "+ signal.getName());
            scheduledFuture.cancel(true);
            executorService.shutdown();
            System.exit(0);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Perform finishing work in the closing hook
            // Precautions:
            // 1. The actions performed here do not consume energy for too long
            // 2. You cannot perform registration again here, remove the operation of closing the hook
            // 3 System.exit() cannot be called here
            System.out.println("do shutdown hook");
        }));

        while (true) {
            Thread.sleep(1000);
        }
    }
}
