package com.ride2go.r2gapi.mapper;

import com.ride2go.core.WeightedItem;
import com.ride2go.core.location.Place;
import com.ride2go.r2gapi.api.dto.WeightedPlaceItemDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class WeightedPlaceItemMapper {


    public abstract WeightedPlaceItemDto map(WeightedItem<Place> placeWeightedItem);
    public abstract WeightedItem<Place> reverseMap(WeightedPlaceItemDto placeWeightedItem);


}
