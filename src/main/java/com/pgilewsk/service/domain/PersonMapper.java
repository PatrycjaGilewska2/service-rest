package com.pgilewsk.service.domain;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    public static PersonDTO mapToDto(PersonEntity personEntity) {
        if (personEntity == null) { return null; }
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setFirstName(personEntity.getFirstName());
        personDTO.setLastName(personEntity.getLastName());
        return personDTO;
    }

    public static List<PersonDTO> mapToDtos(List<PersonEntity> personEntities) {
        List<PersonDTO> personDTOs = new ArrayList<>();
        for (PersonEntity personEntity : personEntities) {
            personDTOs.add(mapToDto(personEntity));
        }
        return personDTOs;
    }

    public static String mapToStrings(List<PersonEntity> personEntities) {
        String stringDtos = "";
        for (PersonEntity personEntity : personEntities) {
            stringDtos += mapToString(personEntity);
        }
        return stringDtos;
    }

    public static String mapToString(PersonEntity personEntity) {
        if (personEntity == null) { return null; }
        return "id: " + personEntity.getId().toString() +
                ", first name: " + personEntity.getFirstName() +
                ", last name: " + personEntity.getLastName() + ";\n";
    }
}

