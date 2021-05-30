package com.demo.cqrseventsourcing.projectorservice.application;

import io.vavr.control.Option;

public interface IUseCase<TInput> {
    Option handle(TInput input);
}
