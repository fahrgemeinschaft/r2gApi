package com.ride2go.r2gapi.api.endpoints;

import com.ride2go.r2gapi.IService;
import com.ride2go.r2gapi.api.dto.ThingDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.api.dto.search.SearchDto;
import com.ride2go.r2gapi.api.sanity.SearchSanitizer;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.core.trip.Trip;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.TripMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
abstract class MarketApiBase<T extends ThingDto> {

    IService<T> marketService;
    SearchSanitizer searchSanitizer;

    protected abstract boolean sanitizeId(String id);

    protected ResponseEntity<T> doGetById(final String id) {
        if (!sanitizeId(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return marketService.findById(UUID.fromString(id))
                .map(t -> ResponseEntity.ok().body(t))
                .orElse(ResponseEntity.notFound().build());
    }


    protected  ResponseEntity<T> doCreate(T data){
        return marketService.create(data)
                .map(t -> ResponseEntity.ok().body(t))
                .orElse(ResponseEntity.unprocessableEntity().build());
    }

    protected ResponseEntity<T> doUpdate(T data){
        return marketService.update(data)
                .map(t -> ResponseEntity.ok().body(t))
                .orElse(ResponseEntity.notFound().build());
    }

    protected ResponseEntity<T> doDeleteById(String id){
        return marketService.findById(UUID.fromString(id))
                .map(t -> doDelete(t))
                .orElse(ResponseEntity.notFound().build());
    }

    protected ResponseEntity<T> doDelete(T data){
        return marketService.delete(data)
                .map(t -> ResponseEntity.ok().body(t))
                .orElse(ResponseEntity.notFound().build());
    }

    protected ResponseEntity<Page<T>> doSearch(SearchDto searchParams) {

        if (!searchSanitizer.sanitizeSearch(searchParams)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return marketService.search(searchParams)
                .map(p -> ResponseEntity.ok(p))
                .orElse(ResponseEntity.noContent().build());

    }



}
