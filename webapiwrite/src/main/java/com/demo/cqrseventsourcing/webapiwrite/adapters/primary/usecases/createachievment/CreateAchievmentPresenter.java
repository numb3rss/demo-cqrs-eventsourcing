package com.demo.cqrseventsourcing.webapiwrite.adapters.primary.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommandPresenter;

public class CreateAchievmentPresenter implements ICommandPresenter {
    private String invalidMessage;

    @Override
    public void invalid(String message) {
        this.invalidMessage = message;
    }
}
