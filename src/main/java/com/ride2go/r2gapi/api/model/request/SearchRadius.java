package com.ride2go.r2gapi.api.model.request;

import com.ride2go.r2gapi.api.model.GeoLocation;
import lombok.Data;

@Data
public class SearchRadius {

    GeoLocation location;
    long radius;
}
