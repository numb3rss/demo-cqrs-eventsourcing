package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.UUID;

public class Hits {
    private Total total;
    private float maxScore;
    private Hit[] hits;

    @JsonProperty("total")
    public Total getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Total total) {
        this.total = total;
    }

    @JsonProperty("max_score")
    public float getMaxScore() {
        return maxScore;
    }

    @JsonProperty("max_score")
    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }

    @JsonProperty("hits")
    public Hit[] getHits() {
        return hits;
    }

    @JsonProperty("hits")
    public void setHits(Hit[] hits) {
        this.hits = hits;
    }
}
