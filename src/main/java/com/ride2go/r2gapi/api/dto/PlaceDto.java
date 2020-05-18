package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PlaceDto extends ThingDto {

    @Schema(description = "A single-line Address")
    String simpleAddress;

    @Schema(description = "An Postal Address of this Place")
    PostalAddressDto address;

    @Schema(description = "Coordinates of this Place, elevation is normalized to 100mNN if unknown")
    GeoCoordinatesDto geo;


}
