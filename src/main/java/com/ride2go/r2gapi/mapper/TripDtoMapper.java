package com.ride2go.r2gapi.mapper;


import com.ride2go.r2gapi.api.dto.MarketDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.model.Market;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TripDtoMapper {

    @Mapping(target = "offer.subject", ignore = true)
    @Mapping(target = "demand.subject", ignore = true)
    public abstract Trip fromDto(TripDto tripDto);


    @AfterMapping
    protected void fillMarkets(TripDto tripDto, @MappingTarget Trip trip) {
        fillSubject(trip.getOffer(), trip);
        fillSubject(trip.getDemand(), trip);
    }

    private void fillSubject(Market market, Trip trip) {
        if (market != null) {
            market.setSubject(trip);
        }
    }



}
