package com.pgilewsk.service.domain;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper {

    public static PersonDTO map(PersonEntity personEntity) {
        if (personEntity == null) {
            return null;
        }

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setFirstName(personEntity.getFirstName());
        personDTO.setLastName(personEntity.getLastName());
        return personDTO;
    }

    public static List<PersonDTO> mapToDTOs(List<PersonEntity> boardGameEntities) {
        List<PersonDTO> boardGameDTOs = new ArrayList<>();
        for (PersonEntity boardGameEntity : boardGameEntities) {
            boardGameDTOs.add(map(boardGameEntity));
        }
        return boardGameDTOs;
    }

}
