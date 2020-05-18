package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class GeoCoordinatesDto extends ThingDto {

    @Schema(description = "The latitude of a location. For example 37.42242 (WGS 84).")
    double latitude;
    @Schema(description = "The longitude of a location. For example -122.08585 (WGS 84).")
    double longitude;

    @Schema(description = "The elevation of a location (WGS 84). ")
    double elevation;
}
