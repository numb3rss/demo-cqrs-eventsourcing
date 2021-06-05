package com.demo.cqrseventsourcing.webapiread.adapters.primary.usecases.retrieveachievment;

import com.demo.cqrseventsourcing.cqrslibrary.IQueryHandler;
import com.demo.cqrseventsourcing.cqrslibrary.IQueryPresenter;
import com.demo.cqrseventsourcing.cqrslibrary.QueryError;
import com.demo.cqrseventsourcing.webapiread.adapters.primary.usecases.retrieveachievment.model.RetrieveByIdResponse;
import com.demo.cqrseventsourcing.webapiread.application.usecases.retrieveachievment.RetrieveAchievmentQuery;
import com.demo.cqrseventsourcing.webapiread.application.usecases.retrieveachievment.RetrieveAchievmentQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("achievments")
public class AchievmentController implements IQueryPresenter<RetrieveAchievmentQueryResult>
{
    private final IQueryHandler<RetrieveAchievmentQuery, RetrieveAchievmentQueryResult> queryHandler;
    private ResponseEntity viewModel;

    public AchievmentController(@Autowired IQueryHandler<RetrieveAchievmentQuery, RetrieveAchievmentQueryResult> queryHandler)
    {
        this.queryHandler = queryHandler;
    }

    @GetMapping("{id}")
    public ResponseEntity retrieveById(@PathVariable UUID id)
    {
        queryHandler.setPresenter(this);
        var query = new RetrieveAchievmentQuery(id);
        queryHandler.handle(query);
        return viewModel;
    }

    @Override
    public void invalid(QueryError queryError)
    {
        this.viewModel = ResponseEntity.status(queryError.getCode()).body(queryError.getMessage());
    }

    @Override
    public void ok(RetrieveAchievmentQueryResult queryResult)
    {
        var response = new RetrieveByIdResponse(queryResult.getId(), queryResult.getName());
        this.viewModel = ResponseEntity.status(200).body(response);
    }
}
