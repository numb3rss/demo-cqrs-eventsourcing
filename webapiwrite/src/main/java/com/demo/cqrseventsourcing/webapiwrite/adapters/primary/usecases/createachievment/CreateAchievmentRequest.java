package com.demo.cqrseventsourcing.webapiwrite.adapters.primary.usecases.createachievment;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CreateAchievmentRequest {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate happenedDate;

    public String getName()
    {
        return name;
    }

    public LocalDate getHappenedDate() {
        return this.happenedDate;
    }
}
