package com.ride2go.r2gapi.api.dto.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ride2go.jackson.BruteForceZonedDateTimeDeserializer;
import com.ride2go.r2gapi.api.dto.search.TimeRangeDto;
import com.ride2go.r2gapi.configuration.WebConfiguration;
import com.ride2go.r2gapi.legacy.model.TransportType;
import com.ride2go.r2gapi.legacy.model.TripType;
import com.ride2go.r2gapi.legacy.search.*;
import com.ride2go.r2gapi.legacy.search.paging.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(description = "searching criteria, including pagination options")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchDto {

    @NonNull
    @Schema(description = "pagination settings")
    PageRequest page;

    @NonNull
    @Schema(description = "what kind of trips to look for")
    List<TripType> tripTypes = new ArrayList<>();

    @NonNull
    @Schema(description = "the area the trip should start from")
    SearchRadius startPoint;

    @NonNull
    @Schema(description = "the area the trip should arrive to")
    SearchRadius endPoint;

    @Schema(description = "in what time range should the trip depart")
    TimeRangeDto departure;

    @Schema(description = "in what time range should the trip arrive")
    TimeRangeDto arrival;

    @NonNull
    @Schema(description = "on which days should the trip reoccur")
    List<WeekDay> reoccurDays = new ArrayList<>();

    @NonNull
    @Schema(description = "is smoking allowed (irrelevant by default)")
    AllowedSmoking smoking;

    @NonNull
    @Schema(description = "are animals allowed (irrelevant by default)")
    AllowedAnimals animals;

    @NonNull
    @Schema(description = "types of transport")
    List<TransportType> transportTypes = new ArrayList<>();

    @NonNull
    @Schema(description = "size of allowed baggage")
    AllowedBaggage baggage;

    @NonNull
    @Schema(description = "desired gender of the driver (irrelevant by default)")
    AllowedGender gender;

    @NonNull
    @Schema(description = "organization associatons")
    List<Organization> organizations = new ArrayList<>();

    @Schema(description = "start of the availability of the trip")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonDeserialize(using = BruteForceZonedDateTimeDeserializer.class)
    ZonedDateTime availabilityStarts;

    @Schema(description = "end of the availability of the trip")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonDeserialize(using = BruteForceZonedDateTimeDeserializer.class)
    ZonedDateTime availabilityEnds;

}
