package com.demo.cqrseventsourcing.projectorbatch;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InMemoryPersonReader implements ItemReader<PersonDTO> {
    private int nextPersonIndex;
    private List<PersonDTO> personData;

    public InMemoryPersonReader() {
        initialize();
    }

    @Override
    public PersonDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        PersonDTO nextPerson = null;

        if (nextPersonIndex < personData.size()) {
            nextPerson = personData.get(nextPersonIndex);
            nextPersonIndex++;
        }
        else {
            nextPersonIndex = 0;
        }

        return nextPerson;
    }

    private void initialize() {
        PersonDTO tony = new PersonDTO();
        tony.setLastName("Tester");
        tony.setFirstName("Tony");

        PersonDTO nick = new PersonDTO();
        nick.setFirstName("Nick");
        nick.setLastName("Newbie");

        PersonDTO ian = new PersonDTO();
        ian.setFirstName("Ian");
        ian.setLastName("intermediate");

        personData = Collections.unmodifiableList(Arrays.asList(tony, nick, ian));
        nextPersonIndex = 0;
    }
}
