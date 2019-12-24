package com.ride2go.r2gapi.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.legacy.model.Animals;
import com.ride2go.r2gapi.legacy.model.Smoking;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class TripDto extends ThingDto {

    ZonedDateTime departureTime;
    ZonedDateTime arrivalTime;

    TransportDto transport;
    List<ParticipationDto> participations;

    /**
     * the seats currently available to this trip.
     * at most Transport.seatingCapacity -1 (driver)
     */
    int availableSeats;

    @JsonView(Views.IncludeTripOfferDemand.class)
    OfferDto offer;

    @JsonView(Views.IncludeTripOfferDemand.class)
    DemandDto demand;

    Smoking smoking;
    Animals animals;

}