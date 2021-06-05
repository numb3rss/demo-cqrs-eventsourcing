package com.demo.cqrseventsourcing.cqrslibrary;

public interface IQueryPresenter<TQueryResult extends IQueryResult>
{
    void invalid(QueryError queryError);
    void ok(TQueryResult queryResult);
}
