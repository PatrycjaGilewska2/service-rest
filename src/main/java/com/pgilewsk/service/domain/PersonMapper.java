package com.pgilewsk.service.domain;

public class PersonMapper {

    private PersonMapper() {
    }

    public static PersonDto mapToDto(Person person) {
        return new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName());
    }

    public static Person mapToEntity(PersonDto personDto) {
        return new Person(personDto.getId(), personDto.getFirstName(), personDto.getLastName());
    }

    public static String mapDtoToString(PersonDto personDto) {
        return "person { id= " + personDto.getId() + ", firstName = " +
                personDto.getFirstName() + ", lastName = " +
                personDto.getLastName() + " } \n";
    }
}
