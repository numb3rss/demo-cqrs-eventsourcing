package com.demo.cqrseventsourcing.webapiwrite.adapters.secondary;

import com.demo.cqrseventsourcing.webapiwrite.application.ports.EventStoreRepository;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.AchievmentAggregate;
import org.springframework.stereotype.Repository;

@Repository
public class EventStoreAchievmentRepository implements EventStoreRepository<AchievmentAggregate> {
    @Override
    public void emit(AchievmentAggregate achievmentAggregate) {

    }
}
