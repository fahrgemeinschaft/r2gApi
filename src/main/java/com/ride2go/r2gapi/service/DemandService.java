package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.api.dto.DemandDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.repository.TripEntityRepository;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.DemandMapper;
import com.ride2go.r2gapi.mapper.SearchMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import com.ride2go.r2gapi.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DemandService extends MarketBaseLegacyService<DemandDto> {

    @Autowired
    DemandMapper demandMapper;

    private static final List<TripType> SUPPORTED_TYPES = Collections.singletonList(TripType.OFFER);

    protected DemandService(ElasticTripRepository elasticTripRepository, TripEntityRepository tripEntityRepository, TripMapper tripMapper, SearchMapper searchMapper, SecurityHelper securityHelper) {
        super(elasticTripRepository, tripEntityRepository, tripMapper, searchMapper, securityHelper);
    }


    @Override
    protected DemandDto map(TripDto trip) {
        return demandMapper.map(trip);
    }

    @Override
    protected TripDto reverseMap(DemandDto data) {
        //FIXME
        return demandMapper.reverseMap(data);
    }

    @Override
    protected Page<DemandDto> map(Page<Trip> trips) {
        return demandMapper.map(trips);
    }

    @Override
    protected List<TripType> supportedTripTypes() {
      return SUPPORTED_TYPES;
    }

}
