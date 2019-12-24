package com.ride2go.r2gapi.api.endpoints;


import com.fasterxml.jackson.annotation.JsonView;
import com.ride2go.r2gapi.api.dto.OfferDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.api.dto.Views;
import com.ride2go.r2gapi.api.sanity.OfferSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.OfferMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/offer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OfferApi extends MarketApiBase<OfferDto> {

    private static final List<TripType> SUPPORTED_TYPES = Collections.singletonList(TripType.OFFER);

    OfferSanitizer offerSanitizer;
    OfferMapper offerMapper;

    public OfferApi(ElasticTripRepository tripRepository, TripMapper tripMapper, OfferSanitizer offerSanitizer, OfferMapper offerMapper) {
        super(tripRepository, tripMapper);
        this.offerSanitizer = offerSanitizer;
        this.offerMapper = offerMapper;
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferDto> getById(@PathVariable final String id) {
        return doGetById(id);
    }

    @JsonView(Views.IncludeMarketSubject.class)
    @PostMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<OfferDto>> search(@RequestBody Search searchParams) {
        return doSearch(searchParams);
    }

    @Override
    protected boolean sanitizeId(String id) {
        return offerSanitizer.sanitizeId(id);
    }

    @Override
    protected OfferDto map(TripDto trip) {
        return offerMapper.map(trip);
    }

    @Override
    protected List<TripType> supportedTypes() {
        return SUPPORTED_TYPES;
    }

    @Override
    protected Page<OfferDto> map(Page<Trip> trips) {
        return offerMapper.map(trips);
    }
}
