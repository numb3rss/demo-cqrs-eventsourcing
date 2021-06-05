package com.demo.cqrseventsourcing.cqrslibrary;

public class QueryError
{
    private String message;
    private int code;

    public QueryError(String message, int code)
    {
        this.message = message;
        this.code = code;
    }

    public String getMessage()
    {
        return this.message;
    }

    public int getCode()
    {
        return this.code;
    }

    public QueryError message(String message)
    {
        this.message = message;
        return this;
    }

    public QueryError code(int code)
    {
        this.code = code;
        return this;
    }
}
