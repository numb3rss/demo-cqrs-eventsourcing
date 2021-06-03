package com.demo.cqrseventsourcing.projectorservice.application.usecases;

import com.demo.cqrseventsourcing.projectorservice.application.IUseCase;
import com.demo.cqrseventsourcing.projectorservice.application.ports.ElasticSearchRepository;
import com.demo.cqrseventsourcing.projectorservice.domain.Achievment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventstore.core.IndexedEvent;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportAchievment implements IUseCase<IndexedEvent> {
    private final ElasticSearchRepository elasticSearchRepository;

    public ImportAchievment(@Autowired ElasticSearchRepository elasticSearchRepository)
    {
        this.elasticSearchRepository = elasticSearchRepository;
    }

    @Override
    public Option handle(IndexedEvent event)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Achievment achievment = mapper.readValue(event.event().data().data().value().utf8String(), Achievment.class);
            elasticSearchRepository.write(achievment);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Option.of(null);
    }
}
