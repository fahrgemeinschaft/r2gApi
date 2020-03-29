package com.ride2go.r2gapi.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Schema(description = "represents a trip demand/offer")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class MarketDto extends ThingDto {

    @Schema(description = "availability of this trip")
    String availability;

    @Schema(description = "start of the availability")
    ZonedDateTime availabilityStarts;

    @Schema(description = "end of the availability")
    ZonedDateTime availabilityEnds;

    @Schema(description = "subject of this trip")
    @JsonView(Views.IncludeMarketSubject.class)
    TripDto subject;

    @Schema(description = "price of the trip")
    long price;

    @Schema(description = "currency of the price")
    String priceCurrency;

    @Schema(description = "the organisation or person representing the marketActor ")
    PersonaDto marketActor;
}
