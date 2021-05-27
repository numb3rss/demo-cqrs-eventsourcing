package com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommand;

import java.time.LocalDate;

public class CreateAchievmentCommand implements ICommand {
    private String name;
    private LocalDate happenedDate;


    public CreateAchievmentCommand(String name, LocalDate happenedDate) {
        this.name = name;
        this.happenedDate = happenedDate;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getHappenedDate() {
        return this.happenedDate;
    }
}
