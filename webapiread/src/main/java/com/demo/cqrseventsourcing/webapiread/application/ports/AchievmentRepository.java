package com.demo.cqrseventsourcing.webapiread.application.ports;

import com.demo.cqrseventsourcing.cqrslibrary.QueryError;
import com.demo.cqrseventsourcing.webapiread.domain.Achievment;
import io.vavr.control.Either;

import java.util.UUID;

public interface AchievmentRepository
{
    Either<QueryError, Achievment> get(UUID id);
}
