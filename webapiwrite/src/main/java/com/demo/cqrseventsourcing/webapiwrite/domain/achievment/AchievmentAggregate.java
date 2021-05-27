package com.demo.cqrseventsourcing.webapiwrite.domain.achievment;

import io.vavr.control.Option;

public class AchievmentAggregate {
    private Achievment achievment;

    public Achievment getAchievment() {
        return this.achievment;
    }

    public AchievmentAggregate(Achievment achievment) {
        this.achievment = achievment;
    }

    public Option<String> isValid()
    {
        if(this.achievment.greaterThanNow()) {
            return Option.of(null);
        }

        return Option.of("La date de l'évènement doit être supérieure où égale à la date du jour");
    }
}
