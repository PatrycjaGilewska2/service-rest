package com.pgilewsk.service.service.impl;

import com.pgilewsk.service.domain.PersonCreator;
import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.domain.PersonMapper;
import com.pgilewsk.service.repository.PersonRepository;
import com.pgilewsk.service.service.PersonService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonCreator personCreator;

    @Override
    public void addPerson(PersonDto dto) {
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

    @Override
    public Optional<String> findPersonById(int id) {
        return findById(id).map(PersonMapper::mapDtoToString);
    }

    @Override
    public String findAllPersons() {
        StringBuilder persons = new StringBuilder();
        for (PersonDto personDto : findAll()) {
            persons.append(PersonMapper.mapDtoToString(personDto));
        }
        return persons.toString();
    }
}
