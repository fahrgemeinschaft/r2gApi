package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * A Persona is a Character or Profile a User chooses to adapt.
 * In most cases this will be a Profile of a Human, however it can also be used as Profile
 * for technical users/machines or Organisations.
 */
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class PersonaDto extends ThingDto {

    String additionalName;
    String familyName;
    String givenName;
    Gender gender;

    List<ContactPointDto> contactPoints;


}