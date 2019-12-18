package com.ride2go.r2gapi.adapter;

import com.ride2go.r2gapi.api.model.Trip;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.api.model.request.Search;
import com.ride2go.r2gapi.legacy.search.TripPreferencesFilter;
import com.ride2go.r2gapi.legacy.search.TripPreferencesParams;
import com.ride2go.r2gapi.legacy.search.paging.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchAdapter {


    @Autowired
    ElasticTripRepository elasticTripRepository;

    @Autowired
    Converter converter;



    public Object tripSearch(Search search){
        //FIXME
        TripPreferencesParams params = converter.toTripParams(search);
        TripPreferencesFilter filter = converter.toTripFilter(search);
        PageRequest page = converter.toPageRequest(search);
        Object elasticResult = elasticTripRepository.findAllTrips(params, filter, page);
        List<Trip> searchResult = converter.convertToTrip(elasticResult);

        return searchResult;
    }

    public Object offerSearch(Search search){
        //FIXME
        TripPreferencesParams params = converter.toTripParams(search);
        TripPreferencesFilter filter = converter.toTripFilter(search);
        PageRequest page = converter.toPageRequest(search);
        Object elasticResult = elasticTripRepository.findAllTrips(params, filter, page);
        List<Trip> searchResult = converter.convertToOffer(elasticResult);

        return searchResult;
    }

    public Object demandSearch(Search search){
        //FIXME
        TripPreferencesParams params = converter.toTripParams(search);
        TripPreferencesFilter filter = converter.toTripFilter(search);
        PageRequest page = converter.toPageRequest(search);
        Object elasticResult = elasticTripRepository.findAllTrips(params, filter, page);
        List<Trip> searchResult = converter.convertToDemand(elasticResult);

        return searchResult;
    }

}
