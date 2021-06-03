package com.demo.cqrseventsourcing.webapiwrite.adapters.primary.usecases.createachievment;

import com.demo.cqrseventsourcing.cqrslibrary.ICommandHandler;
import com.demo.cqrseventsourcing.cqrslibrary.ICommandPresenter;
import com.demo.cqrseventsourcing.webapiwrite.application.usecases.createachievment.CreateAchievmentCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("achievments")
public class AchievmentController implements ICommandPresenter
{
    private final ICommandHandler<CreateAchievmentCommand> commandHandler;
    private ResponseEntity viewModel;

    public AchievmentController(ICommandHandler<CreateAchievmentCommand> commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CreateAchievmentRequest request) {
        this.commandHandler.setPresenter(this);
        var command = new CreateAchievmentCommand(request.getName(), request.getHappenedDate());
        commandHandler.handle(command);
        return this.viewModel;
    }

    @Override
    public void invalid(String message) {
        this.viewModel = ResponseEntity.status(400).body(message);
    }

    @Override
    public void ok(UUID id) {  this.viewModel = ResponseEntity.status(202).body(id); }
}
