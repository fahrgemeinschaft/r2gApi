package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.DemandDto;
import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.api.dto.Views;
import com.ride2go.r2gapi.api.sanity.DemandSanitizer;
import com.ride2go.r2gapi.api.sanity.SearchSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.DemandMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import com.ride2go.r2gapi.service.DemandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/demand")
@Tag(name = "Demands", description = "Demand handling")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DemandApi extends MarketApiBase<DemandDto> {

    private static final List<TripType> SUPPORTED_TYPES = Collections.singletonList(TripType.SEARCH);

    DemandSanitizer demandSanitizer;


    public DemandApi(DemandService demandService, DemandSanitizer demandSanitizer, SearchSanitizer searchSanitizer) {
        super(demandService, searchSanitizer);
        this.demandSanitizer = demandSanitizer;
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create an Deman")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed Data", responseCode = "400", content = @Content)
    public ResponseEntity<DemandDto> create(@Parameter(description = "Offer Data", required = true) @RequestBody final DemandDto demandDto) {
        return doCreate(demandDto);
    }


    @JsonView(Views.IncludeMarketSubject.class)
    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Update an Offer")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed Data", responseCode = "400", content = @Content)
    public ResponseEntity<DemandDto> update(@Parameter(description = "Offer Data", required = true) @RequestBody final DemandDto demandDto) {
        return doUpdate(demandDto);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Delete an Offer")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed Data", responseCode = "400", content = @Content)
    public ResponseEntity<DemandDto> delete(@Parameter(description = "Offer Data", required = true) @RequestBody final DemandDto demandDto) {
        return doDelete(demandDto);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the demand with the given ID")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    public ResponseEntity<DemandDto> deleteById(@Parameter(description = "ID of the demand to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        return doDeleteById(id);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the demand with the given ID")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    public ResponseEntity<DemandDto> getById(@Parameter(description = "ID of the demand to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        return doGetById(id);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Demand searching")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    public ResponseEntity<Page<DemandDto>> search(@Parameter(description = "Search criteria", required = true) @RequestBody Search searchParams) {
        return doSearch(searchParams);
    }

    @Override
    protected boolean sanitizeId(String id) {
        return demandSanitizer.sanitizeId(id);
    }


}
