package com.ride2go.r2gapi.mapper;

import com.ride2go.r2gapi.api.dto.MarketDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.core.trip.Trip;
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
        fillDtoSubject(dto.getOffer(), dto);
        fillDtoSubject(dto.getDemand(), dto);
    }

    private void fillDtoSubject(MarketDto market, TripDto trip) {
        if (market != null) {
            market.setSubject(trip);
        }
    }

    public abstract List<TripDto> toDto(List<Trip> trips);

    public Page<TripDto> toDto(Page<Trip> trips) {
        return Page.of(toDto(trips.getResults()), trips.getPage());
    }


    public abstract Trip fromDto(TripDto tripDto);

    public abstract List<Trip> fromDto(List<TripDto> tripDtoList);

    public Page<Trip> fromDto(Page<TripDto>tripDtos){
        return Page.of(fromDto(tripDtos.getResults()), tripDtos.getPage());
    }


}
