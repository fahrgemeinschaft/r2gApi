package com.ride2go.r2gapi.api.dto.search;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ride2go.jackson.BruteForceZonedDateTimeDeserializer;
import com.ride2go.r2gapi.configuration.WebConfiguration;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@Schema(description = "represents a time range")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeRangeDto {

    @Schema(description = "mean of the time range")
    @JsonProperty
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonDeserialize(using = BruteForceZonedDateTimeDeserializer.class)
    ZonedDateTime time;

    @Schema(description = "duration between the mean and the ends of the time range")
    int toleranceInDays;

}

