package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.Baggage;
import com.ride2go.r2gapi.legacy.model.TransportType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class TransportDto extends ThingDto {


    TransportType transportType;
    int seatingCapacity;
    Baggage cargoVolume;
    PersonaDto owner;
    PersonaDto operator;


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