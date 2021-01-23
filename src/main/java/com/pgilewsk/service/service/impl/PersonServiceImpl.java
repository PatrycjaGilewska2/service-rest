package com.pgilewsk.service.service.impl;

import com.pgilewsk.service.domain.PersonDTO;
import com.pgilewsk.service.repository.PersonRepository;
import com.pgilewsk.service.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonDTO> findAll() {
        return personRepository.findAll();
    }
}
