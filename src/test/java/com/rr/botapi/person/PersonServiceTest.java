package com.rr.botapi.person;

import com.rr.botapi.config.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PersonServiceTest extends AbstractIntegrationTest {

    @Autowired
    private PersonService personService;

    @Test
    void shouldCreatePerson() {
        //GIVEN
        Person expected = person();
        //WHEN
        Person result = personService.createPerson(expected);
        //THEN
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getSurname(), result.getSurname());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getPhone(), result.getPhone());
        assertNotNull(result.getId());
    }

    @Test
    public void shouldGetPerson() {
        //GIVEN
        Person person = person();
        Person expected = personService.createPerson(person);
        //WHEN
        Person result = personService.getPerson(expected.getId());
        //THEN
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.getSurname(), result.getSurname());
        assertEquals(expected.getEmail(), result.getEmail());
        assertEquals(expected.getPhone(), result.getPhone());
    }

    private Person person() {
        return new Person()
                .setName("Marco")
                .setSurname("Polo")
                .setEmail("marco.polo.@gmail.com")
                .setPhone("1234567890");
    }
}