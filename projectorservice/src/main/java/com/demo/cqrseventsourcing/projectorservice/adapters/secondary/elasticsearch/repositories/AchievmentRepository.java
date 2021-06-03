package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.elasticsearch.repositories;

import com.demo.cqrseventsourcing.projectorservice.adapters.secondary.elasticsearch.ElasticSearchClient;
import com.demo.cqrseventsourcing.projectorservice.adapters.secondary.elasticsearch.entities.AchievmentEntity;
import com.demo.cqrseventsourcing.projectorservice.application.ports.ElasticSearchRepository;
import com.demo.cqrseventsourcing.projectorservice.domain.Achievment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AchievmentRepository implements ElasticSearchRepository {
    private final ElasticSearchClient elasticSearchClient;

    public AchievmentRepository(@Autowired ElasticSearchClient elasticSearchClient)
    {
        this.elasticSearchClient = elasticSearchClient;
    }

    @Override
    public Option write(Achievment achievment) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            var achievmentEntity = new AchievmentEntity(achievment.getId(), achievment.getName());
            var jsonBody = mapper.writeValueAsString(achievmentEntity);
            elasticSearchClient.post("/achievments/_doc", jsonBody);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
