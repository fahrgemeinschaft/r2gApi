package com.ride2go.r2gapi.api.dto;

import com.ride2go.r2gapi.legacy.search.AllowedGender;
import com.ride2go.r2gapi.legacy.search.SearchRadius;
import com.ride2go.r2gapi.legacy.search.paging.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Schema(description = "searching criteria, including pagination options")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSearch {

    @NonNull
    @Schema(description = "pagination settings")
    PageRequest page;

    @Schema(description = "location area of the persona")
    SearchRadius location;

    @Schema(description = "age of the persona")
    IntegerRange age;

    @NonNull
    @Schema(description = "gender of the persona")
    AllowedGender gender;

    @NonNull
    @Schema(description = "whether the persona should be single")
    SearchFlag isSingle;

    @NonNull
    @Schema(description = "whether the persona should have an avatar picture")
    SearchFlag hasAvatar;

    @NonNull
    @Schema(description = "whether the persona should be online")
    SearchFlag isOnline;

}
