package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.repositories;

import com.demo.cqrseventsourcing.cqrslibrary.QueryError;
import com.demo.cqrseventsourcing.webapiread.adapters.secondary.InfrastructureException;
import com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.ElasticSearchClient;
import com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities.ElasticSearchMatchQuery;
import com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities.MatchResponseEntity;
import com.demo.cqrseventsourcing.webapiread.application.ports.AchievmentRepository;
import com.demo.cqrseventsourcing.webapiread.domain.Achievment;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Either;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ElasticSearchAchievmentRepository implements AchievmentRepository
{
    private final ElasticSearchClient elasticSearchClient;

    public ElasticSearchAchievmentRepository(@Autowired ElasticSearchClient elasticSearchClient)
    {
        this.elasticSearchClient = elasticSearchClient;
    }

    @Override
    public Either<QueryError, Achievment> get(UUID id)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            var match = new ElasticSearchMatchQuery(id);
            var jsonBody = mapper.writeValueAsString(match);
            var response = elasticSearchClient.get("/achievments/_search", jsonBody);
            var statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200)
            {
                var entityJson = EntityUtils.toString(response.getEntity());
                var matchAchievmentEntity = mapper.readValue(entityJson, MatchResponseEntity.class);
                var achievmentEntity = matchAchievmentEntity.getHits().getHits()[0].getSource();
                var achievment = new Achievment(achievmentEntity.getId(), achievmentEntity.getName());
                return Either.right(achievment);
            }

            return Either.left(new QueryError(response.getStatusLine().getReasonPhrase(), 400));

        }
        catch(Exception exception)
        {
            throw new InfrastructureException("There is an error when retrieving achievment", exception);
        }
    }
}
