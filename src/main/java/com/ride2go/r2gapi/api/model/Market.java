package com.ride2go.r2gapi.api.model;

import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper=false)
public class Market extends Thing{

    String availability;

    ZonedDateTime availabilityStarts;
    ZonedDateTime availabilityEnds;

    Trip subject;

    long price;
    String priceCurrency;

}
