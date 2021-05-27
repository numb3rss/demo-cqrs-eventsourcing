package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.j.SettingsBuilder;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public abstract class EventStoreBaseRepository {
    protected ActorSystem system;
    protected ActorRef connection;

    protected EventStoreBaseRepository(EventStoreConfiguration eventStoreConfiguration) {
        system = ActorSystem.create();
        var settings = new SettingsBuilder()
                .address(new InetSocketAddress(eventStoreConfiguration.getHost(), eventStoreConfiguration.getPort()))
                .connectionName("write event store connection")
                .build();
        connection = system.actorOf(ConnectionActor.getProps(settings));
    }

    public ActorRef buildWriteResult() {
        return system.actorOf(Props.create(EventStoreWriteResult.class));
    }
}
