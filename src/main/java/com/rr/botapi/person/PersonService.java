package com.rr.botapi.person;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createPerson(Person person) {
        log.info("[createPerson] person: {}", person);
        Person saved = personRepository.save(person);
        log.info("[createPerson] saved: {}", person);
        return saved;
    }

    public Person getPerson(Long id) {
        log.info("[getPerson] id: {}", id);
        Person person = personRepository.findPersonById(id);
        log.info("[getPerson] person: {}", person);
        return person;
    }
}
