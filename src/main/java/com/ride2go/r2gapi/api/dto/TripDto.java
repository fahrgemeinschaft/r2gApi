package com.ride2go.r2gapi.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.legacy.model.Animals;
import com.ride2go.r2gapi.legacy.model.Smoking;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.List;

@Schema(description = "represents a trip")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class TripDto extends ThingDto {

    @Schema(description = "departure time of the trip")
    ZonedDateTime departureTime;

    @Schema(description = "arrival time of the trip")
    ZonedDateTime arrivalTime;

    @Schema(description = "information about the vehicle")
    TransportDto transport;

    @Schema(description = "participants")
    List<ParticipationDto> participations;

    @Schema(description = "the seats currently available to this trip. at most Transport.seatingCapacity -1 (driver)")
    int availableSeats;

    @Schema(description = "details of the trip offer (only if this trip is an offer)")
    @JsonView(Views.IncludeTripOfferDemand.class)
    OfferDto offer;

    @Schema(description = "details of the trip demand (only if this trip is a demand)")
    @JsonView(Views.IncludeTripOfferDemand.class)
    DemandDto demand;

    @Schema(description = "smoking preferences of the trip")
    Smoking smoking;

    @Schema(description = "animal transport preferences of the trip")
    Animals animals;

    List<ScheduleDto> schedules;


}