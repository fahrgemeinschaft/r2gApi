package com.ride2go.r2gapi.api.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.util.UUID;


@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class ThingDto extends ApiDataDto {
    UUID id;

    URL url;

    String additionalType;

    String name;

    String image;

    String description;

}