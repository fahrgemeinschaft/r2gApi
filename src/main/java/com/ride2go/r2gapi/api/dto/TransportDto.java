package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.Baggage;
import com.ride2go.r2gapi.legacy.model.TransportType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Schema(description = "represents transport information of a trip")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class TransportDto extends ThingDto {

    @Schema(description = "type of vehicle")
    TransportType transportType;

    @Schema(description = "maximum number of seats (including driver)")
    int seatingCapacity;

    @Schema(description = "how many baggage can be transported")
    Baggage cargoVolume;

    @Schema(description = "owner of the vehicle")
    PersonaDto owner;

    @Schema(description = "operator of the vehicle")
    PersonaDto operator;

    @Schema(description = "color of the vehicle")
    String color;

    @Schema(description = "manufacturer of the vehicle")
    String manufacturer;

    @Schema(description = "model of the vehicle")
    String model;

    @Schema(description = "release date of the vehicle model (things like \"fall 2019\" are valid)")
    String modelDate;

    @Schema(description = "registration ID of the vehicle")
    String registrationId;

}