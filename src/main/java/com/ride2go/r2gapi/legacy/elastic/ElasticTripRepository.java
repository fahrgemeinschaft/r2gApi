package com.ride2go.r2gapi.legacy.elastic;


import com.ride2go.r2gapi.api.model.request.Search;
import com.ride2go.r2gapi.legacy.model.Trip;
import org.elasticsearch.common.unit.Fuzziness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.Operator.AND;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class ElasticTripRepository {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    public Object searchTrip(Search search){

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("title", "spring date elasticsearch")
                        .operator(AND)
                        .fuzziness(Fuzziness.ONE)
                        .prefixLength(3))
                .build();

        List<Trip> trips = elasticsearchTemplate.queryForList(searchQuery, Trip.class);


        return null;
    }
}
