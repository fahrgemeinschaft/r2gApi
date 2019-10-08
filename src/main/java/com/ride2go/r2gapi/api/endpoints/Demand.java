package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.model.request.Search;
import com.ride2go.r2gapi.api.sanity.DemandSanitizer;
import com.ride2go.r2gapi.api.sanity.TripSanitizer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Demand {

    private final DemandSanitizer demandSanitizer;

    public Demand(DemandSanitizer demandSanitizer) {

        this.demandSanitizer = demandSanitizer;

    }


    @GetMapping(path="/demand/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Demand> getById(@PathVariable final String id){
        if(demandSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping(path = "/demand/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Demand>> search(@RequestBody Search searchParams){

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

}
