package com.ride2go.r2gapi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.util.UUID;

@Schema(description = "represents an API object")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ThingDto extends ApiDataDto {
    @Schema(description = "ID of this object")
    UUID id;

    @Schema(description = "URL of this object")
    URL url;

    @Schema(description = "additional type information")
    String additionalType;

    @Schema(description = "name of this object")
    String name;

    @Schema(description = "an image of this object")
    String image;

    @Schema(description = "description of this object")
    String description;

}