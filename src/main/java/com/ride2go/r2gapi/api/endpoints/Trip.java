package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.sanity.TripSanitizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ride2go.r2gapi.api.model.request.Search;
import java.util.List;

@RestController
public class Trip {

    private final TripSanitizer tripSanitizer;

    public Trip(TripSanitizer tripSanitizer) {
        this.tripSanitizer = tripSanitizer;
    }


    @GetMapping(path="/trip/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trip> getById(@PathVariable final String tripId){
        if(tripSanitizer.sanitizeId(tripId)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping(path = "/trip/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Trip>> search(@RequestBody Search searchParams){

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

}
