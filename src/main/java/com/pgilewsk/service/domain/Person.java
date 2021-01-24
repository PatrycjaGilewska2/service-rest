package com.pgilewsk.service.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue
    int personId;
    String firstName;
    String lastName;
}