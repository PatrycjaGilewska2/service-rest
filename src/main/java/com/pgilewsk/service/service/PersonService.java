package com.pgilewsk.service.service;

import com.pgilewsk.service.domain.PersonDTO;

import java.util.List;

public interface PersonService {
    List<PersonDTO> findAll();
    String findAllStrings();
}
