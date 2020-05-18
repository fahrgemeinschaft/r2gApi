package com.ride2go.r2gapi.mapper;

import com.ride2go.core.WeightedItem;
import com.ride2go.core.trip.Trip;
import com.ride2go.r2gapi.api.dto.WeightedTripItemDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class WeightedTripItemMapper {


    public abstract WeightedTripItemDto map(WeightedItem<Trip> placeWeightedItem);
    public abstract WeightedItem<Trip> reverseMap(WeightedTripItemDto placeWeightedItem);


}
