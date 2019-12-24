package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.DemandDto;
import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.Views;
import com.ride2go.r2gapi.api.sanity.DemandSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Demand;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.DemandMapper;
import com.ride2go.r2gapi.mapper.OfferMapper;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DemandApi {

    DemandSanitizer demandSanitizer;
    ElasticTripRepository tripRepository;
    DemandMapper demandMapper;

    @JsonView(Views.IncludeMarketSubject.class)
    @GetMapping(path = "/demand/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DemandDto> getById(@PathVariable final String id) {
        if (demandSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/demand/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<DemandDto>> search(@RequestBody Search searchParams) {
        searchParams.setTripTypes(Collections.singletonList(TripType.SEARCH));
        Page<Trip> trips = tripRepository.findAllTrips(searchParams);
        Page<DemandDto> result = demandMapper.map(trips);
        return ResponseEntity.ok().body(result);
    }

}
