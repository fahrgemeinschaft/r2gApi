package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.sanity.TripSanitizer;
import com.ride2go.r2gapi.api.model.*;
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
    public ResponseEntity<com.ride2go.r2gapi.api.model.Trip> getById(@PathVariable final String id){
        if(tripSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping(path = "/trip/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<com.ride2go.r2gapi.api.model.Trip>> search(@RequestBody Search searchParams){

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

}
