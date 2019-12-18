package com.ride2go.r2gapi.adapter;

import com.ride2go.r2gapi.api.model.Trip;
import com.ride2go.r2gapi.api.model.request.Search;
import com.ride2go.r2gapi.legacy.search.TripPreferencesFilter;
import com.ride2go.r2gapi.legacy.search.TripPreferencesParams;
import com.ride2go.r2gapi.legacy.search.paging.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {

    public TripPreferencesParams toTripParams(Search search) {
        return null; // FIXME
    }

    public TripPreferencesFilter toTripFilter(Search search) {
        return null; // FIXME
    }

    public PageRequest toPageRequest(Search search) {
        return null; // FIXME
    }

    public List<Trip> convertToTrip(Object in){
        return  null; //FIXME

    }

    public List<Trip> convertToOffer(Object elasticResult) {
        return  null; //FIXME

    }
    public List<Trip> convertToDemand(Object elasticResult) {
        return  null; //FIXME

    }
}
