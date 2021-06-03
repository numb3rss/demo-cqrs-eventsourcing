package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

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

@Repository
public class EventStoreAchievmentRepository extends EventStoreBaseRepository implements EventStoreRepository<AchievmentAggregate> {
    public EventStoreAchievmentRepository(@Autowired EventStoreConfiguration eventStoreConfiguration) {
        super(eventStoreConfiguration);
    }

    @Override
    public void emit(AchievmentAggregate achievmentAggregate) {
        try
        {
            var writeResult = this.buildWriteResult();
            var event = new CreateAchievmentEvent(achievmentAggregate.getAchievment().getId(), achievmentAggregate.getAchievment().getName());
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(event);
            final EventData eventData = new EventDataBuilder("create-achievment-event")
                    .eventId(achievmentAggregate.getAchievment().getId())
                    .jsonData(json)
                    .build();
            final WriteEvents writeEvents = new WriteEventsBuilder("achievments")
                    .addEvent(eventData)
                    .expectAnyVersion()
                    .build();
            this.connection.tell(writeEvents, writeResult);
        }
        catch (Exception exception)
        {
            throw new InfrastructureException("There is an issue when writing event", exception);
        }
    }
}
