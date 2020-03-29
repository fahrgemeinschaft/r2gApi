package com.ride2go.r2gapi.mapper;

import com.ride2go.r2gapi.api.dto.search.SearchDto;
import com.ride2go.r2gapi.legacy.search.Search;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class SearchMapper {

    public abstract Search map(SearchDto searchDto);

    public abstract SearchDto reverseMap(Search search);

}
