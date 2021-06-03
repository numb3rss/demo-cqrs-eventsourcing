package com.demo.cqrseventsourcing.projectorservice.application.ports;

import com.demo.cqrseventsourcing.projectorservice.domain.Achievment;
import io.vavr.control.Option;

public interface ElasticSearchRepository {
    Option write(Achievment achievment);
}
