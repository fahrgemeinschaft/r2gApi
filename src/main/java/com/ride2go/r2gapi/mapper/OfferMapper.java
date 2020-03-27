package com.ride2go.r2gapi.mapper;

import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import org.springframework.stereotype.Component;

@Component
public class OfferMapper extends MarketMapper<OfferDto> {

    public OfferMapper(TripMapper tripMapper) {
        super(tripMapper);
    }

    @Override
    public OfferDto map(TripDto trip) {
        return trip.getOffer();
    }

    @Override
    public TripDto reverseMap(OfferDto marketDto) {
        return marketDto.getSubject();
    }
}
