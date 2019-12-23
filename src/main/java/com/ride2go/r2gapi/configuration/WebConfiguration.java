package com.ride2go.r2gapi.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfiguration {

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder ->
                builder
                        .modules(new JavaTimeModule())
                        .deserializers(new ZonedDateTimeDeserializer());
    }

    private static final class ZonedDateTimeDeserializer extends InstantDeserializer<ZonedDateTime> {

        private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mmZ";

        protected ZonedDateTimeDeserializer() {
            super(
                    ZonedDateTime.class,
                    DateTimeFormatter.ofPattern(DATE_TIME_FORMAT),
                    ZonedDateTime::from,
                    a -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId.getId().equals("UTC") ? ZoneOffset.UTC : a.zoneId),
                    a -> ZonedDateTime.ofInstant(Instant.ofEpochSecond(a.integer, a.fraction), a.zoneId.getId().equals("UTC") ? ZoneOffset.UTC : a.zoneId),
                    ZonedDateTime::withZoneSameInstant,
                    false
            );
        }
    }

}
