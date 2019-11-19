package com.ride2go.r2gapi.adapter;

import com.ride2go.r2gapi.api.model.Trip;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {


    List<Trip> convertToTrip(Object in){
        return  null; //FIXME

    }

    public List<Trip> convertToOffer(Object elasticResult) {
        return  null; //FIXME

    }
    public List<Trip> convertToDemand(Object elasticResult) {
        return  null; //FIXME

    }
}
