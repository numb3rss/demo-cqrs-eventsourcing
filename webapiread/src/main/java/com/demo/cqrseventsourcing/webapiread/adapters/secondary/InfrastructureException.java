package com.demo.cqrseventsourcing.webapiread.adapters.secondary;

public class InfrastructureException extends Error {
    public InfrastructureException(String message, Throwable cause){
        super(message, cause);
    }
}
