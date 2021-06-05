package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shards {
    private int total;
    private int successful;
    private int skipped;
    private int failed;

    @JsonProperty("total")
    public int getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("successful")
    public int getSuccessful() {
        return successful;
    }

    @JsonProperty("successful")
    public void setSuccessful(int successful) {
        this.successful = successful;
    }

    @JsonProperty("skipped")
    public int getSkipped() {
        return skipped;
    }

    @JsonProperty("skipped")
    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    @JsonProperty("failed")
    public int getFailed() {
        return failed;
    }

    @JsonProperty("failed")
    public void setFailed(int failed) {
        this.failed = failed;
    }
}
