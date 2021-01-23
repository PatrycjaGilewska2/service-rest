package com.pgilewsk.service.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
}
