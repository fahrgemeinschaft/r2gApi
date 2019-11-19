package com.ride2go.r2gapi.adapter;

import com.ride2go.r2gapi.api.model.Trip;
import com.ride2go.r2gapi.repository.ElasticTripRepository;
import com.ride2go.r2gapi.api.model.request.Search;
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
        Object elasticResult = elasticTripRepository.searchTrip(search);
        List<Trip> searchResult = converter.convertToTrip(elasticResult);

        return searchResult;
    }

    public Object offerSearch(Search search){
        //FIXME
        Object elasticResult = elasticTripRepository.searchTrip(search);
        List<Trip> searchResult = converter.convertToOffer(elasticResult);

        return searchResult;
    }

    public Object demandSearch(Search search){
        //FIXME
        Object elasticResult = elasticTripRepository.searchTrip(search);
        List<Trip> searchResult = converter.convertToDemand(elasticResult);

        return searchResult;
    }

}
