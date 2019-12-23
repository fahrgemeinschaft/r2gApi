package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.sanity.PersonaSanitizer;
import com.ride2go.r2gapi.legacy.model.ContactPoint;
import com.ride2go.r2gapi.legacy.model.Persona;
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
public class PersonaApi {

    private final PersonaSanitizer personaSanitizer;

    public PersonaApi(PersonaSanitizer personaSanitizer) {
        this.personaSanitizer = personaSanitizer;
    }


    @GetMapping(path = "/persona/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> getById(@PathVariable final String id) {
        if (personaSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping(path = "/persona/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Persona>> search(@RequestBody Search searchParams) {

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

    @GetMapping(path = "/persona/{id}/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactPoint>> getContactPoints(@PathVariable final String id) {
        if (personaSanitizer.sanitizeId(id)) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

}
