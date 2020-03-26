package com.ride2go.r2gapi.service;

import com.ride2go.r2gapi.IService;
import com.ride2go.r2gapi.api.dto.TripDto;
import com.ride2go.r2gapi.legacy.elastic.ElasticTripRepository;
import com.ride2go.r2gapi.legacy.model.Trip;
import com.ride2go.r2gapi.legacy.repository.TripEntityRepository;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.TimeRange;
import com.ride2go.r2gapi.legacy.search.TripType;
import com.ride2go.r2gapi.legacy.search.paging.Page;
import com.ride2go.r2gapi.mapper.TripMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class MarketBaseLegacyService<T> implements IService<T> {

    final ElasticTripRepository elasticTripRepository;
    final TripEntityRepository tripEntityRepository;
    final TripMapper tripMapper;

    protected MarketBaseLegacyService(ElasticTripRepository elasticTripRepository, TripEntityRepository tripEntityRepository, TripMapper tripMapper) {
        this.elasticTripRepository = elasticTripRepository;
        this.tripEntityRepository = tripEntityRepository;
        this.tripMapper = tripMapper;
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
    public Page<T> search(Search searchParams) {
        searchParams.setTripTypes(supportedTripTypes());

        Page<Trip> trips = elasticTripRepository.findAllTrips(searchParams);
        return map(trips);
    }

    @Override
    public Optional<T> create(T data){
        //FIXME

     //   tripEntityRepository.saveAndFlush(tripEntity);

        return Optional.empty();
    }

    @Override
    public Optional<T> update(T data){
        //FIXME
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(T data){
        //FIXME
        return Optional.empty();
    }



}
