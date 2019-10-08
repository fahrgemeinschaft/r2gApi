package com.ride2go.r2gapi.api.model;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ApiData{

    public ApiData(){}

    ZonedDateTime created;

    ZonedDateTime modified;

    boolean deleted;

}