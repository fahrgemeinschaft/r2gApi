package com.ride2go.r2gapi.api.endpoints;


import com.ride2go.r2gapi.api.dto.UserSearch;
import com.ride2go.r2gapi.api.sanity.PersonaSanitizer;
import com.ride2go.core.persona.ContactPoint;
import com.ride2go.core.persona.Persona;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/persona")
@Tag(name = "Personas", description = "Persona handling")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PersonaApi {

    PersonaSanitizer personaSanitizer;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the persona with the given ID")
//    @ApiResponse(description = "Successful operation", responseCode = "200")
//    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
//    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    @ApiResponse(description = "Not yet implemented", responseCode = "501", content = @Content)
    public ResponseEntity<Persona> getById(@Parameter(description = "ID of the persona to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        if (personaSanitizer.sanitizeId(id)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Persona searching")
//    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Not yet implemented", responseCode = "501", content = @Content)
    public ResponseEntity<List<Persona>> search(@Parameter(description = "Search criteria", required = true) @RequestBody UserSearch searchParams) {

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);

    }

    @GetMapping(path = "/{id}/contact", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the contact infos of the persona with the given ID")
//    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
//    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    @ApiResponse(description = "Not yet implemented", responseCode = "501", content = @Content)
    public ResponseEntity<List<ContactPoint>> getContactPoints(@Parameter(description = "ID of the persona to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        if (personaSanitizer.sanitizeId(id)) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

}
