package com.demo.cqrseventsourcing.webapiread.application.usecases.retrieveachievment;

import com.demo.cqrseventsourcing.cqrslibrary.IQueryPresenter;
import com.demo.cqrseventsourcing.cqrslibrary.QueryError;

public class RetrieveAchievmentQueryPresenter implements IQueryPresenter<RetrieveAchievmentQueryResult>
{
    private QueryError queryError;
    private RetrieveAchievmentQueryResult retrieveAchievmentQueryResult;

    @Override
    public void invalid(QueryError queryError) {
        this.queryError = queryError;
        this.retrieveAchievmentQueryResult = null;
    }

    @Override
    public void ok(RetrieveAchievmentQueryResult queryResult)
    {
        this.retrieveAchievmentQueryResult = queryResult;
        this.queryError = null;
    }
}
