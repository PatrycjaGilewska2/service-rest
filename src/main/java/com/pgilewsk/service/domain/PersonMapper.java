package com.pgilewsk.service.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PersonMapper {

    static Logger logger = LoggerFactory.getLogger(PersonMapper.class);
    static final String PREFIX = "person { ";
    static final String SUFFIX = " }\n";
    static final String COMMA = ", ";
    static final String ID = "id = ";
    static final String FIRST_NAME = "firstName = ";
    static final String LAST_NAME = "lastName = ";

    public static PersonDto mapToDto(Person person) {
        PersonDto personDto = new PersonDto(person.getPersonId(), person.getFirstName(), person.getLastName());
        logger.info("Entity successfully converted to Dto: {}", personDto);
        return personDto;
    }

    public static Person mapToEntity(PersonDto personDto) {
        Person person = new Person(personDto.getId(), personDto.getFirstName(), personDto.getLastName());
        logger.info("Dto successfully converted to Entity: {}", person);
        return person;
    }

    public static String mapDtoToString(PersonDto personDto) {
        String stringDto = PREFIX + ID + personDto.getId() + COMMA + FIRST_NAME +
                personDto.getFirstName() + COMMA + LAST_NAME +
                personDto.getLastName() + SUFFIX;
        logger.info("Dto successfully converted to String: {}", stringDto);
        return stringDto;
    }

//    @NotNull
    public static String mapEntityToString(Person person) {
        String stringEntity = PREFIX + ID + person.getPersonId() + COMMA + FIRST_NAME +
                person.getFirstName() + COMMA + LAST_NAME +
                person.getLastName() + SUFFIX;
        logger.info("Entity successfully converted to String");
        return stringEntity;
    }
}
