package com.demo.cqrseventsourcing.webapiwrite.domain.achievment;

public class AchievmentAggregate {
    private Achievment achievment;

    public Achievment getAchievment() {
        return this.achievment;
    }

    public AchievmentAggregate(Achievment achievment) {
        this.achievment = achievment;
    }

    public boolean isValid()
    {
        return this.achievment.greaterThanNow();
    }
}
