package com.pgilewsk.service.domain;

import lombok.*;

@Value
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class PersonDto {
    int id;
    String firstName;
    String lastName;
}
