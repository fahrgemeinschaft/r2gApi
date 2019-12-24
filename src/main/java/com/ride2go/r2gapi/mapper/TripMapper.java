package com.ride2go.r2gapi.mapper;

import com.ride2go.r2gapi.api.dto.MarketDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TripMapper {

    @Mapping(target = "offer.subject", ignore = true)
    @Mapping(target = "demand.subject", ignore = true)
    public abstract TripDto toDto(Trip trip);

    @AfterMapping
    protected void fillMarkets(Trip trip, @MappingTarget TripDto dto) {
        fillSubject(dto.getOffer(), dto);
        fillSubject(dto.getDemand(), dto);
    }

    private void fillSubject(MarketDto market, TripDto trip) {
        if (market != null) {
            market.setSubject(trip);
        }
    }

    public abstract List<TripDto> toDto(List<Trip> trips);

    public Page<TripDto> toDto(Page<Trip> trips) {
        return Page.of(toDto(trips.getResults()), trips.getPage());
    }

}
