package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@EnableConfigurationProperties(EventStoreConfiguration.class)
@ConfigurationProperties("eventstore")
@Component
public class EventStoreConfiguration {
    private String host;
    private int port;

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
}
