package com.ride2go.r2gapi.api.sanity;

import com.ride2go.r2gapi.api.dto.search.SearchDto;
import com.ride2go.r2gapi.api.dto.search.TimeRangeDto;

import java.time.ZonedDateTime;

public class SearchSanitizer {


    public boolean sanitizeSearch(SearchDto search){

        if(null == search.getDeparture()){
            TimeRangeDto tr = new TimeRangeDto();
            tr.setTime(ZonedDateTime.now());
            tr.setToleranceInDays(1);
            search.setDeparture(tr);
        }
        return true;
    }
}
