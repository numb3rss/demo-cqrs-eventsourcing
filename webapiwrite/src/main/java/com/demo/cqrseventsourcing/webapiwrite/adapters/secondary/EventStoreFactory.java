package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import eventstore.akka.Settings;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.j.SettingsBuilder;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class EventStoreFactory {
    private final ActorSystem system;
    private final Settings settings;

    public EventStoreFactory() {
        system = ActorSystem.create();
        settings = new SettingsBuilder()
                .address(new InetSocketAddress("127.0.0.1", 1113))
                .connectionName("write event store connection")
                .build();
    }

    public ActorRef CreateConnection() {
        return system.actorOf(ConnectionActor.getProps(settings));
    }

    public ActorRef CreateWriteResult() {
        return system.actorOf(Props.create(EventStoreWriteResult.class));
    }
}
