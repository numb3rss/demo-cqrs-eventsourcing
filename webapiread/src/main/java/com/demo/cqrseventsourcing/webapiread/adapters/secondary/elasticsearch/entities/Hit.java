package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Hit {
    private String index;
    private String type;
    private String id;
    private float score;
    private AchievmentEntity source;

    @JsonProperty("_index")
    public String getIndex() {
        return index;
    }

    @JsonProperty("_index")
    public void setIndex(String index) {
        this.index = index;
    }

    @JsonProperty("_type")
    public String getType() {
        return type;
    }

    @JsonProperty("_type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("_score")
    public float getScore() {
        return score;
    }

    @JsonProperty("_score")
    public void setScore(float score) {
        this.score = score;
    }

    @JsonProperty("_source")
    public AchievmentEntity getSource() {
        return source;
    }

    @JsonProperty("_source")
    public void setSource(AchievmentEntity source) {
        this.source = source;
    }
}
