package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.UUID;

@Schema(description = "represents an API object")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class ApiDataDto {

    @Schema(description = "creation time of this object")
    ZonedDateTime created;

    @Schema(description = "last modification time of this object")
    ZonedDateTime modified;

    @Schema(description = "a flag, whether this object is deleted")
    boolean deleted;

    @Schema(description = "id of creator")
    UUID createdBy;

    @Schema(description = "id of modifier")
    UUID modifiedBy;
}