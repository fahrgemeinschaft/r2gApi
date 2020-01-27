package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Schema(description = "represents a contact of a persona")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ContactPointDto extends ThingDto {

    @Schema(description = "email")
    String email;

    @Schema(description = "fax")
    String faxnumber;

    @Schema(description = "telephone")
    String telephone;

}