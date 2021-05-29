package com.demo.cqrseventsourcing.projectorservice;

import akka.actor.ActorSystem;
import eventstore.akka.SubscriptionObserver;
import eventstore.core.Event;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import eventstore.j.SettingsBuilder;

import java.io.Closeable;
import java.net.InetSocketAddress;

public class AchievmentsRunnable implements Runnable {
    private final EsConnection connection;

    public AchievmentsRunnable() {
        var system = ActorSystem.create();
        var settings = new SettingsBuilder()
                .address(new InetSocketAddress("127.0.0.1", 1113))
                .connectionName("read subscription")
                .build();
        connection = EsConnectionFactory.create(system, settings);
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
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onClose() {

            }
        }, false, null);
    }
}
