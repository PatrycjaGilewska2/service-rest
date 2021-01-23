package com.pgilewsk.service;

import com.pgilewsk.service.domain.PersonDTO;
import com.pgilewsk.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    String home() {
        return "Hello World!";
    }

    @GetMapping("/persons")
    private List<PersonDTO> findAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/string")
    private String findAllStrings() {
        return personService.findAllStrings();
    }

}