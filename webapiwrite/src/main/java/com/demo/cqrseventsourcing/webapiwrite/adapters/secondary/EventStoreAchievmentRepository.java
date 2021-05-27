package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

import akka.actor.ActorRef;
import com.demo.cqrseventsourcing.webapiwrite.adapters.secondary.event.CreateAchievmentEvent;
import com.demo.cqrseventsourcing.webapiwrite.application.ports.EventStoreRepository;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.AchievmentAggregate;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventstore.core.EventData;
import eventstore.core.WriteEvents;
import eventstore.j.EventDataBuilder;
import eventstore.j.WriteEventsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public class EventStoreAchievmentRepository implements EventStoreRepository<AchievmentAggregate> {
    private final ActorRef connection;
    private final EventStoreFactory eventStoreFactory;

    public EventStoreAchievmentRepository(@Autowired EventStoreFactory eventStoreFactory) {
        this.eventStoreFactory = eventStoreFactory;
        connection = this.eventStoreFactory.CreateConnection();
    }

    @Override
    public void emit(AchievmentAggregate achievmentAggregate) {
        try
        {
            var writeResult = this.eventStoreFactory.CreateWriteResult();
            var event = new CreateAchievmentEvent("test");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(event);
            final EventData eventData = new EventDataBuilder("create-achievment-event")
                    .eventId(UUID.randomUUID())
                    .jsonData(json)
                    .build();
            final WriteEvents writeEvents = new WriteEventsBuilder("achievments")
                    .addEvent(eventData)
                    .expectAnyVersion()
                    .build();
            connection.tell(writeEvents, writeResult);
        }
        catch (Exception exception)
        {

        }
    }
}
