package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
class Transport extends Thing {

    enum TransportType{
        CAR,
        PLANE,
        BOAT,
    }


    TransportType transportType;
    int seatingCapacity;
    int cargoVolume;
    Persona owner;
    Persona operator;


    String color;
    String manufacturer;
    /**
     *
     */
    String model;
    /**
     * The "date" description this model was relaeased. things like "fall 2019" are valid.
     */
    String modelDate;
    String registrationId;

}