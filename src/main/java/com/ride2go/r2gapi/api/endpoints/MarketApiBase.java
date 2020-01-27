package com.ride2go.r2gapi.api.endpoints;

import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.TripMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
abstract class MarketApiBase<T> {

    ElasticTripRepository tripRepository;
    TripMapper tripMapper;

    protected ResponseEntity<T> doGetById(final String id) {
        if (!sanitizeId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return tripRepository.findById(UUID.fromString(id))
                .map(tripMapper::toDto)
                .map(this::map)
                .map(t -> ResponseEntity.ok().body(t))
                .orElse(ResponseEntity.notFound().build());
    }

    protected abstract boolean sanitizeId(String id);

    protected abstract T map(TripDto trip);

    protected Page<T> doSearch(Search searchParams) {
        searchParams.setTripTypes(supportedTypes());
        Page<Trip> trips = tripRepository.findAllTrips(searchParams);
        return map(trips);
    }

    protected abstract List<TripType> supportedTypes();

    protected abstract Page<T> map(Page<Trip> trips);

}
