package com.demo.cqrseventsourcing.webapiwrite.domain.achievment;

import java.time.LocalDate;
import java.util.UUID;

public class Achievment {
    private String name;
    private UUID id;
    private LocalDate hasOccuredDate;

    public Achievment(LocalDate hasOccuredDate, String name) {
        this.id = UUID.randomUUID();
        this.hasOccuredDate = hasOccuredDate;
        this.name = name;
    }

    public String getName() { return this.name; }

    public LocalDate getHasOccuredDate() {
        return this.hasOccuredDate;
    }

    public UUID getId() { return this.id; }

    public boolean greaterThanNow() {
        return this.hasOccuredDate.compareTo(LocalDate.now()) >= 0;
    }
}
