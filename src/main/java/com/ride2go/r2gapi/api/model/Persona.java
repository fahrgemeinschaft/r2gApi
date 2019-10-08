package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
/**
 * A Persona is a Character or Profile a User chooses to adapt.
 * In most cases this will be a Profile of a Human, however it can also be used as Profile
 * for technical users/machines or Organisations.
 */
@Data
@Builder
@EqualsAndHashCode(callSuper=false)
class Persona extends Thing{

    String additionalName;
    String familyName;
    String givenName;

    List<ContactPoint> contactPoints;


}