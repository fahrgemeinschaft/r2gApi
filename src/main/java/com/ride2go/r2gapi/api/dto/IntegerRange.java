package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Schema(description = "Represents a number range with integer boundaries. Both boundaries are optional and inclusive.")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IntegerRange {

    @Schema(description = "lower boundary")
    Long from;

    @Schema(description = "upper boundary")
    Long to;

}
