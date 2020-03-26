package com.ride2go.r2gapi;

import com.ride2go.r2gapi.api.dto.ThingDto;
import com.ride2go.r2gapi.legacy.search.Search;
import com.ride2go.r2gapi.legacy.search.paging.Page;

import java.util.Optional;
import java.util.UUID;

public interface IService<T extends ThingDto> {


    Optional<T> findById(UUID id);

    Page<T> search(Search searchParams);

    Optional<T> create(T data);

    Optional<T> update(T data);

    Optional<T> delete(T data);
}
