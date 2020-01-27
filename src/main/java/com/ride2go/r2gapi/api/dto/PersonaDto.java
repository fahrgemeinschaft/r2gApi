package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Schema(description = "Represents a persona. A Persona is a Character or Profile a User chooses to adapt. In most cases this will be a Profile of a Human, however it can also be used as Profile for technical users/machines or Organisations.")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class PersonaDto extends ThingDto {

    @Schema(description = "additional name of the persona")
    String additionalName;

    @Schema(description = "femily name of the persona")
    String familyName;

    @Schema(description = "given name of the persona")
    String givenName;

    @Schema(description = "gender of the persona")
    Gender gender;

    @Schema(description = "contacts of the persona")
    List<ContactPointDto> contactPoints;


}