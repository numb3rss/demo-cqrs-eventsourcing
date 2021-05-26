package com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommandHandler;
import com.demo.cqrseventsourcing.webapiwrite.adapters.secondary.EventStoreAchievmentRepository;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.Achievment;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.AchievmentAggregate;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateAchievment implements ICommandHandler<CreateAchievmentCommand, CreateAchievmentResult> {
    private EventStoreAchievmentRepository repository;

    public CreateAchievment(@Autowired EventStoreAchievmentRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public CreateAchievmentResult handle(CreateAchievmentCommand createAchievmentCommand) {
        var result = new CreateAchievmentResult();
        var location = new Location("Lille");
        var achievment = new Achievment(createAchievmentCommand.getHappenedDate(), location);
        var achievmentAggregate = new AchievmentAggregate(achievment);

        if(achievmentAggregate.isValid()){
            repository.emit(achievmentAggregate);
            result.status = "OK";
        }
        else {
            result.status = "KO";
        }

        return result;
    }
}
