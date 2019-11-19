package com.ride2go.r2gapi.legacy.elastic;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

@Service
public interface ElasticTripRepository extends ElasticsearchRepository<Trip, String> {





}
