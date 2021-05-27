package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

public class InfrastructureException extends Error {
    public InfrastructureException(String message, Exception exception) {
        super(message, exception);
    }
}
