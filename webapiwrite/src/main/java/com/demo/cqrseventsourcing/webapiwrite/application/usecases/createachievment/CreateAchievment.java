package com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommandHandler;
import com.demo.cqrseventsourcing.cqrslibrary.ICommandPresenter;
import com.demo.cqrseventsourcing.webapiwrite.adapters.primary.usecases.createachievment.CreateAchievmentPresenter;
import com.demo.cqrseventsourcing.webapiwrite.adapters.secondary.EventStoreAchievmentRepository;
import com.demo.cqrseventsourcing.webapiwrite.adapters.secondary.InfrastructureException;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.Achievment;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.AchievmentAggregate;
import com.demo.cqrseventsourcing.webapiwrite.domain.achievment.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAchievment implements ICommandHandler<CreateAchievmentCommand> {
    private ICommandPresenter presenter;
    private EventStoreAchievmentRepository repository;

    public CreateAchievment(@Autowired EventStoreAchievmentRepository repository)
    {
        this.presenter = new CreateAchievmentPresenter();
        this.repository = repository;
    }

    @Override
    public void setPresenter(ICommandPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(CreateAchievmentCommand createAchievmentCommand) {
        var location = new Location("Lille");
        var achievment = new Achievment(createAchievmentCommand.getHappenedDate(), location);
        var achievmentAggregate = new AchievmentAggregate(achievment);
        var commandError = achievmentAggregate.isValid();

        if(commandError.isEmpty()){
            repository.emit(achievmentAggregate);
            return;
        }

        this.presenter.invalid(commandError.getOrNull());
    }
}
