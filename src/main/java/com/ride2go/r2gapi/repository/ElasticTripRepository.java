package com.ride2go.r2gapi.repository;


import com.ride2go.r2gapi.api.model.Transport;
import com.ride2go.r2gapi.api.model.request.Search;
import com.ride2go.r2gapi.api.model.request.SearchRadius;
import org.springframework.stereotype.Service;

@Service
public class ElasticTripRepository {



    public Object searchTrip(Search search){
        //FIXME: do a search here
        //FIXME: mabye use a differen model or params for Searcdh
        return new Object();
    }

}
