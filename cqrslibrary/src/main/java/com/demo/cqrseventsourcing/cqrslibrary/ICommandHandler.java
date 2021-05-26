package com.demo.cqrseventsourcing.cqrslibrary;

public interface ICommandHandler<TCommand, TOutput extends Object> {
    TOutput handle(TCommand command);
}
