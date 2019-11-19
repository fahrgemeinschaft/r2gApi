package com.ride2go.r2gapi.api.model.request;

import com.ride2go.r2gapi.api.model.GeoLocation;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Search {

    SearchRadius startPoint;
    SearchRadius endPoint;

    ZonedDateTime departureTime;
    ZonedDateTime arrivalTime;

    boolean nonSmoking;
    boolean allowAnimals;
    boolean allowCargo;

    ZonedDateTime availabilityStarts;
    ZonedDateTime availabilityEnds;

}
