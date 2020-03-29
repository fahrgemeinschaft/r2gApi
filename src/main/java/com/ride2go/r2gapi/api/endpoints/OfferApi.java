package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.*;
import com.ride2go.r2gapi.api.dto.search.SearchDto;
import com.ride2go.r2gapi.api.sanity.OfferSanitizer;
import com.ride2go.r2gapi.api.sanity.SearchSanitizer;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.service.OfferService;
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

@RestController
@RequestMapping("/offer")
@Tag(name = "Offers", description = "Offer handling")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferApi extends MarketApiBase<OfferDto> {


    OfferSanitizer offerSanitizer;

    public OfferApi( OfferService offerService, OfferSanitizer offerSanitizer, SearchSanitizer searchSanitizer) {
        super(offerService, searchSanitizer);
        this.offerSanitizer = offerSanitizer;
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create an Offer")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed Data", responseCode = "422", content = @Content)
    public ResponseEntity<OfferDto> create(@Parameter(description = "Offer Data", required = true) @RequestBody final OfferDto offerDto) {
        return doCreate(offerDto);
    }


    @JsonView(Views.IncludeMarketSubject.class)
    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Update an Offer")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed Data", responseCode = "422", content = @Content)
    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content)
    public ResponseEntity<OfferDto> update(@Parameter(description = "Offer Data", required = true) @RequestBody final OfferDto offerDto) {
        return doUpdate(offerDto);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Delete an Offer")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed Data", responseCode = "422", content = @Content)
    @ApiResponse(description = "Entity Not Found", responseCode = "404", content = @Content)
    public ResponseEntity<OfferDto> delete(@Parameter(description = "Offer Data", required = true) @RequestBody final OfferDto offerDto) {
        return doDelete(offerDto);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the demand with the given ID")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    public ResponseEntity<OfferDto> deleteById(@Parameter(description = "ID of the demand to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        return doDeleteById(id);
    }


    @JsonView(Views.IncludeMarketSubject.class)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Returns the offer with the given ID")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Malformed ID", responseCode = "400", content = @Content)
    @ApiResponse(description = "Not existing ID", responseCode = "404", content = @Content)
    public ResponseEntity<OfferDto> getById(@Parameter(description = "ID of the offer to find", example = "01234567-89ab-cdef-0123-456789abcdef", required = true) @PathVariable final String id) {
        return doGetById(id);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Offer searching")
    @ApiResponse(description = "Successful operation", responseCode = "200")
    @ApiResponse(description = "Nothing found", responseCode = "204")
    public ResponseEntity<Page<OfferDto>> search(@Parameter(description = "Search criteria", required = true) @RequestBody SearchDto searchParams) {

        return doSearch(searchParams);

    }

    @Override
    protected boolean sanitizeId(String id) {
        return offerSanitizer.sanitizeId(id);
    }


}
