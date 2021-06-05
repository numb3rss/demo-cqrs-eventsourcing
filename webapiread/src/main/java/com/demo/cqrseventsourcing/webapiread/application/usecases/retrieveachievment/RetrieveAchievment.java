package com.demo.cqrseventsourcing.webapiread.application.usecases.retrieveachievment;

import com.demo.cqrseventsourcing.cqrslibrary.IQueryHandler;
import com.demo.cqrseventsourcing.cqrslibrary.IQueryPresenter;
import com.demo.cqrseventsourcing.webapiread.application.ports.AchievmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrieveAchievment implements IQueryHandler<RetrieveAchievmentQuery, RetrieveAchievmentQueryResult>
{
    private final AchievmentRepository achievmentRepository;
    private IQueryPresenter<RetrieveAchievmentQueryResult> presenter;

    public RetrieveAchievment(@Autowired AchievmentRepository achievmentRepository){
        this.achievmentRepository = achievmentRepository;
        presenter = new RetrieveAchievmentQueryPresenter();
    }

    @Override
    public void setPresenter(IQueryPresenter<RetrieveAchievmentQueryResult> presenter) {
        this.presenter = presenter;
    }

    @Override
    public void handle(RetrieveAchievmentQuery query) {
        var either = achievmentRepository.get(query.getId());

        if (either.isLeft()){
            this.presenter.invalid(either.getLeft());
            return;
        }

        var right = either.getOrNull();
        var queryResult = new RetrieveAchievmentQueryResult(right.getId(), right.getName());
        this.presenter.ok(queryResult);
    }
}
