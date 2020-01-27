package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.ParticipationRole;
import com.ride2go.r2gapi.legacy.model.ParticipationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Schema(description = "represents a trip participation")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ParticipationDto extends ThingDto {

    @Schema(description = "role of the participant in this trip")
    ParticipationRole role;

    @Schema(description = "participation status")
    ParticipationStatus status;

    @Schema(description = "participating persona")
    PersonaDto participant;

}