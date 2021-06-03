package com.demo.cqrseventsourcing.webapiwrite.adapters.primary.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommandPresenter;

import java.util.UUID;

public class CreateAchievmentPresenter implements ICommandPresenter {
    private String invalidMessage;
    private UUID id;

    @Override
    public void invalid(String message) {
        this.invalidMessage = message;
    }

    @Override
    public void ok(UUID id) { this.id = id; }
}
