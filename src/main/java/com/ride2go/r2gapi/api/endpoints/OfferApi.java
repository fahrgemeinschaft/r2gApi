package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.sanity.OfferSanitizer;
import com.ride2go.r2gapi.legacy.model.Offer;
import com.ride2go.r2gapi.legacy.search.Search;
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
public class OfferApi {

    private final OfferSanitizer offerSanitizer;

    public OfferApi(OfferSanitizer offerSanitizer) {
        this.offerSanitizer = offerSanitizer;
    }


    @GetMapping(path = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getById(@PathVariable final String id) {
        if (offerSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping(path = "/offer/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Offer>> search(@RequestBody Search searchParams) {

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }
}
