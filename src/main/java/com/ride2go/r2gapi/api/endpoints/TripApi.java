package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.api.dto.Views;
import com.ride2go.r2gapi.api.sanity.TripSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.TripMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TripApi {

    TripSanitizer tripSanitizer;
    ElasticTripRepository tripRepository;
    TripMapper tripMapper;

    @JsonView(Views.IncludeTripOfferDemand.class)
    @GetMapping(path = "/trip/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TripDto> getById(@PathVariable final String id) {
        if (tripSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @JsonView(Views.IncludeTripOfferDemand.class)
    @PostMapping(path = "/trip/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<TripDto>> search(@RequestBody Search searchParams) {
        Page<Trip> trips = tripRepository.findAllTrips(searchParams);
        Page<TripDto> result = tripMapper.toDto(trips);
        return ResponseEntity.ok().body(result);

    }

    @JsonView(Views.IncludeTripOfferDemand.class)
    @PostMapping(path = "/trip/search/minimal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TripDto>> searchMinimal(@RequestBody Search searchParams) {

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

}
