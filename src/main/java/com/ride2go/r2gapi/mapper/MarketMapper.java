package com.ride2go.r2gapi.mapper;

import com.ride2go.r2gapi.api.dto.MarketDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.core.trip.Trip;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
abstract class MarketMapper<T extends MarketDto> {

    TripMapper tripMapper;

    public abstract T map(TripDto trip);
    public abstract TripDto reverseMap(T marketDto);

    public List<T> map(List<Trip> trips) {
        return trips.stream()
                .map(tripMapper::toDto)
                .map(this::map)
                .collect(Collectors.toList());
    }

    public Page<T> map(Page<Trip> trips) {
        return Page.of(map(trips.getResults()), trips.getPage());
    }

}
