package com.demo.cqrseventsourcing.cqrslibrary;

import java.util.UUID;

public interface ICommandPresenter {
    void invalid(String message);
    void ok(UUID id);
}
