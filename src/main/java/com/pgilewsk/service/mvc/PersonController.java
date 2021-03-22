package com.pgilewsk.service.mvc;

import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.service.PersonService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonController {

    //    @Autowired
    PersonService personService;

    @Autowired
    PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/persons")
    public Integer addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping(value = "/persons/{id}")
    public Optional<String> findPersonById(@PathVariable("id") int id) {
        Optional<String> person = personService.findPersonById(id);
        if (person.isPresent()) return person;
        throw new NullPointerException();
    }

    @GetMapping(value = "/persons")
    public String findAllPersons() {
        return personService.findAllPersons();
    }

    @GetMapping(value = "/dtos")
    public List<PersonDto> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/dtos/{id}")
    public Optional<PersonDto> findById(@PathVariable("id") int id) {
        Optional<PersonDto> personDto = personService.findById(id);
        if (personDto.isPresent()) return personDto;
        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
}