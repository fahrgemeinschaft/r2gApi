package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.net.URL;
import java.util.UUID;


@Data
@EqualsAndHashCode(callSuper=false)
public class Thing extends ApiData {
    UUID id;

    URL url;

    String additionalType;

    String name;

    String image;

    String description;

}