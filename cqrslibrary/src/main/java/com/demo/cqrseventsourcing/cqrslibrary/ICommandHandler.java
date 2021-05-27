package com.demo.cqrseventsourcing.cqrslibrary;

public interface ICommandHandler<TCommand extends ICommand> {
    void setPresenter(ICommandPresenter presenter);
    void handle(TCommand command);
}
