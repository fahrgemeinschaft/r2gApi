package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Participation extends Thing {

    enum ParticipationRole {
        DRIVER,
        OPERATOR,
        PASSENGER
    }

    enum ParticipationStatus {
        ACCEPTED,
        REQUESTED,
        REJECTED
    }

    ParticipationRole role;
    ParticipationStatus status;
    Persona participant;
}