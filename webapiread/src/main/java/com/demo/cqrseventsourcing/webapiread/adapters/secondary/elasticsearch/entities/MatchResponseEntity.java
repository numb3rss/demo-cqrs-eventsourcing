package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
public class MatchResponseEntity {
    private int took;
    private boolean timedOut;
    private Shards shards;
    private Hits hits;

    @JsonProperty("took")
    public int getTook() {
        return took;
    }

    @JsonProperty("took")
    public void setTook(int took) {
        this.took = took;
    }

    @JsonProperty("timed_out")
    public boolean isTimedOut() {
        return timedOut;
    }

    @JsonProperty("timed_out")
    public void setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
    }

    @JsonProperty("_shards")
    public Shards getShards() {
        return shards;
    }

    @JsonProperty("_shards")
    public void setShards(Shards shards) {
        this.shards = shards;
    }

    @JsonProperty("hits")
    public Hits getHits() {
        return hits;
    }

    @JsonProperty("hits")
    public void setHits(Hits hits) {
        this.hits = hits;
    }
}
