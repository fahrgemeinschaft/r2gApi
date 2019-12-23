package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.sanity.TripSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.paging.Page;
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

    @GetMapping(path = "/trip/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trip> getById(@PathVariable final String id) {
        if (tripSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping(path = "/trip/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Trip>> search(@RequestBody Search searchParams) {
        Page<Trip> result = tripRepository.findAllTrips(searchParams);
        return ResponseEntity.ok().body(result);

    }

    @PostMapping(path = "/trip/search/minimal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trip>> searchMinimal(@RequestBody Search searchParams) {

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

}
