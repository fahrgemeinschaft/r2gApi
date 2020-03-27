package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.repository.TripEntityRepository;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.OfferMapper;
import com.ride2go.r2gapi.mapper.TripDtoMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import com.ride2go.r2gapi.security.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OfferService extends MarketBaseLegacyService<OfferDto> {

    @Autowired
    OfferMapper offerMapper;


    private static final List<TripType> SUPPORTED_TYPES = Collections.singletonList(TripType.OFFER);

    protected OfferService(ElasticTripRepository elasticTripRepository, TripEntityRepository tripEntityRepository, TripMapper tripMapper, SecurityHelper securityHelper) {
        super(elasticTripRepository, tripEntityRepository, tripMapper, securityHelper);
    }


    @Override
    protected OfferDto map(TripDto trip) {
        return offerMapper.map(trip);
    }

    @Override
    protected TripDto reverseMap(OfferDto data) {
        return offerMapper.reverseMap(data);
    }

    @Override
    protected Page<OfferDto> map(Page<Trip> trips) {
        return offerMapper.map(trips);
    }

    @Override
    protected List<TripType> supportedTripTypes() {
      return SUPPORTED_TYPES;
    }

}
