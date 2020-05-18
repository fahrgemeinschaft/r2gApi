package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class WeightedTripItemDto extends ApiDataDto {

    @Schema(description = "Trip Data")
    TripDto item;

    @Schema(description = "the weight or order of this Trip")
    int weight;
}
