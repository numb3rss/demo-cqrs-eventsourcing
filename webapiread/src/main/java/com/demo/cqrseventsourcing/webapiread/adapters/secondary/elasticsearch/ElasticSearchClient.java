package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElasticSearchClient
{
    private final RestClient restClient;

    public ElasticSearchClient(@Autowired ElasticSearchConfiguration elasticSearchConfiguration) {
        restClient = RestClient.builder(
                new HttpHost(elasticSearchConfiguration.getHost(), elasticSearchConfiguration.getPort(), elasticSearchConfiguration.getScheme())).build();
    }

    public void close() throws IOException {
        restClient.close();
    }

    public Response get(String resource, String jsonBody) throws IOException
    {
        var request = new Request(
                "GET",
                resource
        );
        request.setJsonEntity(jsonBody);

        return restClient.performRequest(request);
    }
}
