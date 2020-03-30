package com.ride2go.r2gapi.api.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ride2go.jackson.BruteForceZonedDateTimeDeserializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;

@Schema(description = "A schedule defines a repeating time period used to describe a regularly occurring Event.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    @Schema(description = "Day of Week this Event is scheduled")
    DayOfWeek byDay;

    @Schema(description = "Month of year this Event is scheduled")
    Month byMonth;

    @Schema(description = "Day of Month. Must be between 1 -31")
    int byDayOfMonth;

    @Schema(description = "earliest Start Date of Interval")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonDeserialize(using = BruteForceZonedDateTimeDeserializer.class)
    ZonedDateTime startDate;

    @Schema(description = "End Date of Interval")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonDeserialize(using = BruteForceZonedDateTimeDeserializer.class)
    ZonedDateTime endDate;

    @Schema(description = "earliest Start Date of Interval")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonDeserialize(using = BruteForceZonedDateTimeDeserializer.class)
    List<ZonedDateTime> exceptDates;

    @Schema(description = "number of repetitions, 0 for neverending")
    int repeatCount;

    @Schema(description = "timezone of this schedule")
    TimeZone scheduleTimezone;
}
