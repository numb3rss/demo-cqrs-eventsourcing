package com.demo.cqrseventsourcing.webapiwrite.domain.achievment;

import java.time.LocalDate;
import java.util.UUID;

public class Achievment {
    private UUID id;
    private LocalDate hasOccuredDate;
    private Location location;

    public LocalDate getHasOccuredDate() {
        return this.hasOccuredDate;
    }

    public Achievment(LocalDate hasOccuredDate, Location location) {
        this.id = UUID.randomUUID();
        this.hasOccuredDate = hasOccuredDate;
        this.location = location;
    }

    public boolean greaterThanNow() {
        return this.hasOccuredDate.compareTo(LocalDate.now()) >= 0;
    }
}
