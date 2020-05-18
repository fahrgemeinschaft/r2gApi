package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.core.trip.Trip;
import com.ride2go.r2gapi.legacy.repository.TripEntityRepository;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.SearchMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import com.ride2go.r2gapi.security.SecurityHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService extends MarketBaseLegacyService<TripDto> {

    private static List<TripType> SUPPORTED_TYPES = new ArrayList<>(2);

    static {
        SUPPORTED_TYPES.add(TripType.OFFER);
        SUPPORTED_TYPES.add(TripType.SEARCH);
    }



    protected TripService(ElasticTripRepository elasticTripRepository, TripEntityRepository tripEntityRepository, TripMapper tripMapper, SearchMapper searchMapper, SecurityHelper securityHelper) {
        super(elasticTripRepository, tripEntityRepository, tripMapper, searchMapper, securityHelper);
    }

    @Override
    protected TripDto map(TripDto trip) {
        return trip;
    }

    @Override
    protected TripDto reverseMap(TripDto data) {
        return data;
    }

    @Override
    protected Page<TripDto> map(Page<Trip> trips) {
        return tripMapper.toDto(trips);
    }

    @Override
    protected List<TripType> supportedTripTypes() {
        return SUPPORTED_TYPES;
    }

    @Override
    protected void lockDefaultSearchTripType(Search search) {
        //do nothing. we can search for all triptypes
    }
}
