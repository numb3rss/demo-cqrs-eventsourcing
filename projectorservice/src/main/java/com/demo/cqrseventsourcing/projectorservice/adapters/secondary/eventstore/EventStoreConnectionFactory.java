package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore;

import akka.actor.ActorSystem;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import eventstore.j.SettingsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class EventStoreConnectionFactory {
    private final EsConnection connection;

    public EventStoreConnectionFactory(@Autowired EventStoreConfiguration eventStoreConfiguration) {
        var system = ActorSystem.create();
        var settings = new SettingsBuilder()
                .address(new InetSocketAddress(eventStoreConfiguration.getHost(), eventStoreConfiguration.getPort()))
                .connectionName("read subscription")
                .build();
        connection = EsConnectionFactory.create(system, settings);
    }

    public EsConnection getConnection() {
        return connection;
    }
}
