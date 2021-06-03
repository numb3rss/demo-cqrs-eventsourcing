package com.demo.cqrseventsourcing.projectorservice.adapters.secondary.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ElasticSearchClient {
    private final RestClient restClient;

    public ElasticSearchClient(@Autowired ElasticSearchConfiguration elasticSearchConfiguration) {
        restClient = RestClient.builder(
                new HttpHost(elasticSearchConfiguration.getHost(), elasticSearchConfiguration.getPort(), elasticSearchConfiguration.getScheme())).build();
    }

    public void close() throws IOException {
        restClient.close();
    }

    public void post(String resource, String jsonBody)
    {
        var request = new Request(
            "POST",
            resource
        );
        request.setJsonEntity(jsonBody);

        Cancellable cancellable = restClient.performRequestAsync(request,
                new ResponseListener() {
                    @Override
                    public void onSuccess(Response response) {

                    }

                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
    }
}
