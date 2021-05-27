package com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment;

public class CreateAchievmentCommandError {
    private String message;
    private int code;

    public CreateAchievmentCommandError(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
