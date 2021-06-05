package com.demo.cqrseventsourcing.webapiread.adapters.secondary.elasticsearch.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ElasticSearchMatchQuery {
    private ElasticSearchQuery query;

    public ElasticSearchMatchQuery(UUID id)
    {
        query = new ElasticSearchQuery(id);
    }

    @JsonProperty("query")
    public ElasticSearchQuery getQuery()
    {
        return query;
    }

    @JsonProperty("query")
    public void setQuery(ElasticSearchQuery query)
    {
        this.query = query;
    }

    public class ElasticSearchQuery {
        private ElasticSearchMatch match;

        public ElasticSearchQuery(UUID id)
        {
            this.match = new ElasticSearchMatch(id);
        }

        @JsonProperty("match")
        public ElasticSearchMatch getMatch()
        {
            return match;
        }

        @JsonProperty("match")
        public void setMatch(ElasticSearchMatch match)
        {
            this.match = match;
        }

        public class ElasticSearchMatch
        {
            private Id id;

            public ElasticSearchMatch(UUID id)
            {
                this.id = new Id(id);
            }

            @JsonProperty("id")
            public Id getId()
            {
                return id;
            }

            @JsonProperty("id")
            public void setId(Id id)
            {
                this.id = id;
            }

            public class Id
            {
                private UUID id;

                public Id(UUID id)
                {
                    this.id = id;
                }

                @JsonProperty("query")
                public UUID getId()
                {
                    return id;
                }

                @JsonProperty("query")
                public void setId(UUID id)
                {
                    this.id = id;
                }
            }
        }
    }
}
