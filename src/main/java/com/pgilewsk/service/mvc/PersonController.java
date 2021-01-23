package com.pgilewsk.service.mvc;

import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    private List<PersonDto> getAllPersons() {
        return personService.findAll();
    }

    @PostMapping("/persons")
    private void addPerson(@RequestBody PersonDto personDto) {
        personService.savePerson(personDto);
    }

    @GetMapping(value = "/persons/{id}")
    private Optional<PersonDto> findPersonById(@PathVariable("id") int id) {
        return personService.findById(id);
    }
}