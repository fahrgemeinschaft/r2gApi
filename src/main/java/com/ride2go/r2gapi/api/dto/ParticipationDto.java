package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.model.ParticipationRole;
import com.ride2go.r2gapi.legacy.model.ParticipationStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ParticipationDto extends ThingDto {

    ParticipationRole role;
    ParticipationStatus status;
    PersonaDto participant;

}