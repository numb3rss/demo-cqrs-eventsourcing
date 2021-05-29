package com.demo.cqrseventsourcing.projectorbatch;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class LogPersonWriter implements ItemWriter<Person> {
    @Override
    public void write(List<? extends Person> persons) throws Exception {
        System.out.println(persons);
    }
}
