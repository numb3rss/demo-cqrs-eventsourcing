package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary.event;

import java.util.UUID;

public class CreateAchievmentEvent {
    private UUID id;
    private String name;

    public CreateAchievmentEvent(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
