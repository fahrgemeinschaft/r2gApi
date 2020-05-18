package com.ride2go.r2gapi.api.dto;

import com.ride2go.core.Thing;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class PostalAddressDto extends ThingDto {

    @Schema(description = "Street and House Number or Flat")
    String streetAddress;

    @Schema(description = "ZIP Code")
    String postalCode;

    @Schema(description = "Name of Country")
    String country;

    @Schema(description = "ISO three-letter Language Code")
    String countryCode;


}
