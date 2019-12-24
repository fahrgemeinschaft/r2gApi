package com.ride2go.r2gapi.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MarketDto extends ThingDto {

    String availability;

    ZonedDateTime availabilityStarts;
    ZonedDateTime availabilityEnds;

    @JsonView(Views.IncludeMarketSubject.class)
    TripDto subject;

    long price;
    String priceCurrency;

}
