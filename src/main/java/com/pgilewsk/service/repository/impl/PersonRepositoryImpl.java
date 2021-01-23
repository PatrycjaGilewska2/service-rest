package com.pgilewsk.service.repository.impl;

import com.pgilewsk.service.domain.DataUtil;
import com.pgilewsk.service.domain.PersonDTO;
import com.pgilewsk.service.domain.PersonEntity;
import com.pgilewsk.service.domain.PersonMapper;
import com.pgilewsk.service.repository.PersonRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private List<PersonEntity> personEntities = new ArrayList<>();

    public PersonRepositoryImpl() {
        personEntities.add(DataUtil.PERSON_1);
        personEntities.add(DataUtil.PERSON_2);
    }

    @Override
    public List<PersonDTO> findAll() {
        return PersonMapper.mapToDTOs(personEntities);
    }
}
