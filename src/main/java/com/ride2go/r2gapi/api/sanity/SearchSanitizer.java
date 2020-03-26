package com.ride2go.r2gapi.api.sanity;

import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TimeRange;

import java.time.ZonedDateTime;

public class SearchSanitizer {


    public boolean sanitizeSearch(Search search){

        if(null == search.getDeparture()){
            TimeRange tr = new TimeRange();
            tr.setTime(ZonedDateTime.now());
            tr.setToleranceInDays(1);
            search.setDeparture(tr);
        }
        return true;
    }
}
