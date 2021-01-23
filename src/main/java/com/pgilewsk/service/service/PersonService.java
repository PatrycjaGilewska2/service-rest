package com.pgilewsk.service.service;

import com.pgilewsk.service.domain.PersonDto;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<PersonDto> findAll();

    Optional<PersonDto> findById(int id);

    void savePerson(PersonDto dto);
}
