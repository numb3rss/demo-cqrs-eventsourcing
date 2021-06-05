package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(ElasticSearchConfiguration.class)
@ConfigurationProperties("elasticsearch")
@Component
public class ElasticSearchConfiguration {
    private String host;
    private int port;
    private String scheme;

    public String getHost() {
        return this.host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getScheme() { return this.scheme; }

    public void setScheme(String scheme) { this.scheme = scheme; }
}
