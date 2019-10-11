package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Trip extends Thing {

    ZonedDateTime arrivalTime;
    ZonedDateTime departureTime;

    Transport transport;
    List<Participation> participations;

    /**
     * the seats currently available to this trip.
     * at most Transport.seatingCapacity -1 (driver)
     */
    int availableSeats;

    Offer offer;
    Demand demand;

    boolean nonSmoking;
    boolean allowAnimals;
    boolean allowCargo;


}