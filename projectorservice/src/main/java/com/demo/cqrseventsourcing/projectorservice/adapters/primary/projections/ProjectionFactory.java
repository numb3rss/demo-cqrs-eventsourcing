package com.demo.cqrseventsourcing.projectorservice.adapters.primary.projections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProjectionFactory {
    private final AchievmentProjection achievmentProjection;

    public ProjectionFactory(@Autowired AchievmentProjection achievmentProjection){
        this.achievmentProjection = achievmentProjection;
    }

    public List<IProjection> getProjections()
    {
        var projections = new ArrayList<IProjection>();
        projections.add(achievmentProjection);
        return Collections.unmodifiableList(projections);
    }
}
