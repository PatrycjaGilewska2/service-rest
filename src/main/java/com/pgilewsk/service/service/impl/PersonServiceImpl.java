package com.pgilewsk.service.service.impl;

import com.pgilewsk.service.domain.Person;
import com.pgilewsk.service.domain.PersonCreator;
import com.pgilewsk.service.domain.PersonDto;
import com.pgilewsk.service.domain.PersonMapper;
import com.pgilewsk.service.repository.PersonRepository;
import com.pgilewsk.service.service.PersonService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pgilewsk.service.domain.PersonMapper.*;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;

    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public int addPerson(PersonDto dto) {
        Person savedPerson = personRepository.save(PersonCreator.create(dto));
        logger.info("Person was successfully saved: {}", mapEntityToString(savedPerson));
        return savedPerson.getPersonId();
    }

    @Override
    public List<PersonDto> findAll() {
        List<PersonDto> persons = new ArrayList<>();
        personRepository.findAll().forEach(person ->
                persons.add(mapToDto(person))
        );
        logger.info("Found {} persons", persons.size());
        return persons;
    }

    @Override
    public Optional<PersonDto> findById(int id) {
        Optional<PersonDto> personDto = personRepository.findById(id).map(PersonMapper::mapToDto);
        if (personDto.isPresent()) {
            logger.info("Found person dto: {}", personDto.get());
            return personDto;
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> findPersonById(int id) {
        return findById(id).map(PersonMapper::mapDtoToString);
    }

    @Override
    public String findAllPersons() {
        StringBuilder persons = new StringBuilder();
        for (PersonDto personDto : findAll()) {
            persons.append(mapDtoToString(personDto));
        }
        return persons.toString();
    }
}
