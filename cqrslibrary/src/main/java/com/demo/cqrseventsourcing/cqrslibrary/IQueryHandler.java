package com.demo.cqrseventsourcing.cqrslibrary;

public interface IQueryHandler<TQuery extends IQuery, TQueryResult extends IQueryResult>
{
    void setPresenter(IQueryPresenter<TQueryResult> presenter);
    void handle(TQuery query);
}
