package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.Views;
import com.ride2go.r2gapi.api.sanity.OfferSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
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

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class OfferApi {

    OfferSanitizer offerSanitizer;
    ElasticTripRepository tripRepository;
    OfferMapper offerMapper;

    @JsonView(Views.IncludeMarketSubject.class)
    @GetMapping(path = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDto> getById(@PathVariable final String id) {
        if (offerSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/offer/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<OfferDto>> search(@RequestBody Search searchParams) {
        searchParams.setTripTypes(Collections.singletonList(TripType.OFFER));
        Page<Trip> trips = tripRepository.findAllTrips(searchParams);
        Page<OfferDto> result = offerMapper.map(trips);
        return ResponseEntity.ok().body(result);
    }
}
