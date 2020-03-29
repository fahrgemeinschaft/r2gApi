package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.api.dto.ThingDto;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.api.dto.search.SearchDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.repository.TripEntityRepository;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.SearchMapper;
import com.ride2go.r2gapi.mapper.TripMapper;
import com.ride2go.r2gapi.security.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class MarketBaseLegacyService<T extends ThingDto> extends AbstractAuthenticatingService<T> {
    private final Logger logger = LoggerFactory.getLogger(MarketBaseLegacyService.class);

    final TripEntityRepository tripEntityRepository;
    final TripMapper tripMapper;
    final SearchMapper searchMapper;

    protected MarketBaseLegacyService(ElasticTripRepository elasticTripRepository, TripEntityRepository tripEntityRepository, TripMapper tripMapper,SearchMapper searchMapper, SecurityHelper securityHelper) {
        super(securityHelper, elasticTripRepository);
        this.tripEntityRepository = tripEntityRepository;
        this.tripMapper = tripMapper;
        this.searchMapper = searchMapper;
    }

    protected abstract T map(TripDto trip);
    protected abstract TripDto reverseMap(T data);
    protected abstract Page<T> map(Page<Trip> trips);
    protected abstract List<TripType> supportedTripTypes();



    @Override
    public Optional<T> findById(UUID id){
        return elasticTripRepository.findById(id)
                .map(tripMapper::toDto)
                .map(this::map);
    }

    @Override
    public Optional<Page<T>> search(SearchDto searchParams) {

        Search search = searchMapper.map(searchParams);

        search.setTripTypes(supportedTripTypes());
        try {
            Page<Trip> trips = elasticTripRepository.findAllTrips(search);
            if (trips != null) {
                return Optional.of(map(trips));
            }
        }catch (Exception e){
            logger.error("search failed with error", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<T> create(T data){
        if(canUserCreate(data)){


        }
     //   tripEntityRepository.saveAndFlush(tripEntity);
        return Optional.empty();
    }

    @Override
    public Optional<T> update(T data){
        if(canUserUpdate(data)){

        }
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(T data){
        if(canUserUpdate(data)){

        }
        return Optional.empty();
    }

/*
    private TripEntity toTripEntity(T data){
        TripDto tripDto = reverseMap(data);

    }

    private ElasticTrip toElasticTrip(T data){
        TripDto tripDto = reverseMap(data);

    }

*/

}
