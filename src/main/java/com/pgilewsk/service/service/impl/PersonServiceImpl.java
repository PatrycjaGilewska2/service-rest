package com.pgilewsk.service.service.impl;

import com.pgilewsk.service.domain.PersonCreator;
import com.pgilewsk.service.domain.PersonMapper;
import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.repository.PersonRepository;
import com.pgilewsk.service.service.PersonService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonCreator personCreator;

    @Override
    public void savePerson(PersonDto dto) {
        PersonCreator.create(dto);
        personRepository.save(PersonCreator.create(dto));
    }

    @Override
    public List<PersonDto> findAll() {
        List<PersonDto> persons = new ArrayList<>();
        personRepository.findAll().forEach(person ->
                persons.add(PersonMapper.mapToDto(person))
        );
        return persons;
    }

    @Override
    public Optional<PersonDto> findById(int id) {
        return personRepository.findById(id).map(PersonMapper::mapToDto);
    }
}
