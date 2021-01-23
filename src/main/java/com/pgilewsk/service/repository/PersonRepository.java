package com.pgilewsk.service.repository;

import com.pgilewsk.service.domain.PersonDTO;

import java.util.List;

public interface PersonRepository {
    List<PersonDTO> findAll();
}
