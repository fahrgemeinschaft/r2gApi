package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.IService;
import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Offer;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.repository.TripEntityRepository;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.OfferMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OfferService extends MarketBaseLegacyService<OfferDto> {

    @Autowired
    OfferMapper offerMapper;
    private static final List<TripType> SUPPORTED_TYPES = Collections.singletonList(TripType.OFFER);

    protected OfferService(ElasticTripRepository elasticTripRepository, TripEntityRepository tripEntityRepository, TripMapper tripMapper) {
        super(elasticTripRepository, tripEntityRepository, tripMapper);
    }


    @Override
    protected OfferDto map(TripDto trip) {
        return offerMapper.map(trip);
    }

    @Override
    protected TripDto reverseMap(OfferDto data) {
        //FIXME
        return null;
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
