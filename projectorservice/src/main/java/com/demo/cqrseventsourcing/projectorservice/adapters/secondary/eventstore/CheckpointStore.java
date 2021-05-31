package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.eventstore;

import com.fasterxml.jackson.databind.ObjectMapper;
import eventstore.core.*;
import eventstore.j.EsConnection;
import eventstore.j.EventDataBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class CheckpointStore {
    private final EsConnection connection;
    private final String checkpointStreamPrefix = "checkpoint:";
    private final String streamName;

    public CheckpointStore(@Autowired EventStoreConnectionFactory eventStoreConnectionFactory)
    {
        connection = eventStoreConnectionFactory.getConnection();
        streamName = checkpointStreamPrefix + "projector";
    }

    public Position.Exact getCheckpoint()
    {
        try
        {
            var slice = Await.result(connection.readStreamEventsBackward(streamName, EventNumber.Current(), 1, false, null), Duration.Inf());
            var eventData = slice.events().head().data();
            ObjectMapper mapper = new ObjectMapper();
            var json = eventData.data().value().utf8String();
            Checkpoint checkpoint = mapper.readValue(json, Checkpoint.class);
            return new Position.Exact(checkpoint.getCommitPosition(), checkpoint.getPreparePosition());
        }
        catch (StreamNotFoundException streamNotFoundException)
        {
            storeCheckpoint(null);
            setStreamMaxCont();

            return new Position.Exact(0L, 0L);
        }
        catch (Exception interruptedException)
        {
            throw new Error("");
        }
    }

    public void storeCheckpoint(Position.Exact position) {
        try
        {
            var checkpoint = new Checkpoint(0L, 0L);
            if(position != null)
            {
                checkpoint = new Checkpoint(position.commitPosition(), position.preparePosition());
            }
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(checkpoint);
            var eventsData = new ArrayList<EventData>();
            eventsData.add(new EventDataBuilder("$checkpoint")
                    .eventId(UUID.randomUUID())
                    .jsonData(json)
                    .build());
            var exepectedVersion = ExpectedVersion.apply(-2L);
            connection.writeEvents(streamName, exepectedVersion, eventsData, null);
        }
        catch(Exception exception)
        {

        }
    }

    private void setStreamMaxCont()
    {
        try
        {
            var metadata = Await.result(connection.getStreamMetadataBytes(streamName, null), Duration.Inf());

            if(metadata.length == 0)
            {
                connection.setStreamMetadata(streamName, ExpectedVersion.apply(-2L), new byte[1], null);
            }
        }
        catch (Exception exception)
        {

        }
    }
}
