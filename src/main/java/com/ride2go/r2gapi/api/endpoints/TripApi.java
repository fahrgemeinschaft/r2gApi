package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.api.dto.Views;
import com.ride2go.r2gapi.api.sanity.TripSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.TripMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
@Tag(name = "Trips", description = "Trip handling")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TripApi {

    TripSanitizer tripSanitizer;
    ElasticTripRepository tripRepository;
    TripMapper tripMapper;

    @JsonView(Views.IncludeTripOfferDemand.class)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the trip with the given ID")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    public ResponseEntity<TripDto> getById(@Parameter(description = "ID of the trip to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        if (!tripSanitizer.sanitizeId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return tripRepository.findById(UUID.fromString(id))
                .map(tripMapper::toDto)
                .map(t -> ResponseEntity.ok().body(t))
                .orElse(ResponseEntity.notFound().build());
    }

    @JsonView(Views.IncludeTripOfferDemand.class)
    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Trip searching")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    public Page<TripDto> search(@Parameter(description = "Search criteria", required = true) @RequestBody Search searchParams) {
        searchParams.setTripTypes(Arrays.asList(TripType.OFFER, TripType.SEARCH));
        Page<Trip> trips = tripRepository.findAllTrips(searchParams);
        return tripMapper.toDto(trips);
    }

    @JsonView(Views.IncludeTripOfferDemand.class)
    @PostMapping(path = "/search/minimal", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Minimal trip searching")
    @ApiResponse(description = "Not yet implemented", responseCode = "501", content = @Content)
    public ResponseEntity<Page<TripDto>> searchMinimal(@Parameter(description = "Search criteria", required = true) @RequestBody Search searchParams) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

}
