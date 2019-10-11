package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.sanity.OfferSanitizer;
import com.ride2go.r2gapi.api.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ride2go.r2gapi.api.model.request.Search;
import java.util.List;

@RestController
public class Offer {

    private final OfferSanitizer offerSanitizer;

    public Offer(OfferSanitizer offerSanitizer) {
        this.offerSanitizer = offerSanitizer;
    }


    @GetMapping(path="/offer/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<com.ride2go.r2gapi.api.model.Offer> getById(@PathVariable final String id){
        if(offerSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping(path = "/offer/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<com.ride2go.r2gapi.api.model.Offer>> search(@RequestBody Search searchParams){

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }
}
