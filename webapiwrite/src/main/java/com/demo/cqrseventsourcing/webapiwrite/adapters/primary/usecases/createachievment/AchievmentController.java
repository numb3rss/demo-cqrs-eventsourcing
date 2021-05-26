package com.demo.cqrseventsourcing.webapiwrite.adapters.primary.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommandHandler;
import com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment.CreateAchievmentCommand;
import com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment.CreateAchievmentResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("achievments")
public class AchievmentController
{
    private final ICommandHandler<CreateAchievmentCommand, CreateAchievmentResult> commandHandler;

    public AchievmentController(ICommandHandler<CreateAchievmentCommand, CreateAchievmentResult> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/create")
    public String create(@RequestBody CreateAchievmentRequest request) {
        var command = new CreateAchievmentCommand(request.getName(), request.getHappenedDate());
        var result = commandHandler.handle(command);
        return result.status;
    }
}
