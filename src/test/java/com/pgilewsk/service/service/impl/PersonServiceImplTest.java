package com.pgilewsk.service.service.impl;

import com.pgilewsk.service.domain.Person;
import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.repository.PersonRepository;
import com.pgilewsk.service.service.PersonService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PersonServiceImplTest {

    private final String PERSON = "person { id = 1, firstName = Anna, lastName = Kowalska }\n";

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeAll
    void init() {
        List<Person> personExamples = getPersonExamples();
        personExamples.forEach(person ->
                personRepository.save(person));
    }


    @Test
    void shouldAddNewPerson() {
        //when
        int newId = personService.addPerson(getPersonDtoExample());
        //then
        Optional<Person> personById = personRepository.findById(4);
        assertEquals(4, newId);
        personById.ifPresent(person -> {
            assertEquals(4, person.getPersonId());
            assertEquals("Katarzyna", person.getFirstName());
            assertEquals("Nosek", person.getLastName());
        });

    }

    @Test
    void shouldFindPersonStringById() {
        //when
        Optional<String> personString = personService.findPersonById(1);
        //then
        personString.ifPresent(person ->
                assertEquals(PERSON, person));
    }

    @Test
    void shouldfindAllPersonsString() {
        //when
        String allPersonDtos = personService.findAllPersons();
        //then
        assertFalse(allPersonDtos.isEmpty());
        assertTrue(allPersonDtos.contains(PERSON));
    }

    @Test
    void shouldFindAllPersonDtos() {
        //when
        List<PersonDto> allPersonDtos = personService.findAll();
        //then
        assertEquals(3, allPersonDtos.size());
        assertEquals(1, allPersonDtos.get(0).getId());
        assertEquals(2, allPersonDtos.get(1).getId());
        assertEquals(3, allPersonDtos.get(2).getId());
        assertEquals("Anna", allPersonDtos.get(0).getFirstName());
        assertEquals("Hubert", allPersonDtos.get(1).getFirstName());
        assertEquals("Joanna", allPersonDtos.get(2).getFirstName());
        assertEquals("Kowalska", allPersonDtos.get(0).getLastName());
        assertEquals("Nowacki", allPersonDtos.get(1).getLastName());
        assertEquals("Bauer", allPersonDtos.get(2).getLastName());
    }

    @Test
    void shouldFindPersonDtoById() {
        //when
        Optional<PersonDto> personDtoById = personService.findById(1);
        //then
        personDtoById.ifPresent(personDto -> {
                    assertEquals(1, personDto.getId());
                    assertEquals("Anna", personDto.getFirstName());
                    assertEquals("Kowalska", personDto.getLastName());
                }
        );
    }

    private PersonDto getPersonDtoExample() {
        return new PersonDto(4, "Katarzyna", "Nosek");
    }

    private List<Person> getPersonExamples() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Anna", "Kowalska"));
        persons.add(new Person(2, "Hubert", "Nowacki"));
        persons.add(new Person(3, "Joanna", "Bauer"));
        return persons;
    }
}