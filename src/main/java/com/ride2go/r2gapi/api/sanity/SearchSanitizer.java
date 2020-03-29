package com.ride2go.r2gapi.api.sanity;

import com.ride2go.r2gapi.api.dto.search.SearchDto;
import com.ride2go.r2gapi.api.dto.search.TimeRangeDto;
import com.ride2go.r2gapi.legacy.search.SearchRadius;

import java.time.ZonedDateTime;

public class SearchSanitizer {


    public boolean sanitizeSearch(SearchDto search){

        if(null == search.getDeparture()){
            TimeRangeDto tr = new TimeRangeDto();
            tr.setTime(ZonedDateTime.now());
            tr.setToleranceInDays(1);
            search.setDeparture(tr);
        }
        final boolean saneStart = sanitizeSearchRadius(search.getStartPoint());
        final boolean saneEnd = sanitizeSearchRadius(search.getEndPoint());

        return saneStart || saneEnd;
    }

    private boolean sanitizeSearchRadius(SearchRadius searchRadius){
        if(searchRadius!=null){
            if(searchRadius.getRadius()<=0){
                searchRadius.setRadius(1);
            }
            return true;
        }
        return false;
    }
}
