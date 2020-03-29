package com.ride2go.r2gapi.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.ride2go.r2gapi.api.sanity.SearchSanitizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfiguration {
/*
    @Bean
    Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder ->
                builder
                        //.modules(new JavaTimeModule())
                        //.deserializers(new ZonedDateTimeDeserializer());

    }
*/
    public static final class ZonedDateTimeDeserializer extends InstantDeserializer<ZonedDateTime> {

        private final Logger logger = LoggerFactory.getLogger(ZonedDateTimeDeserializer.class);

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
            logger.info("DLgkifhjdölfkshglködfshgjloifödshjgldföiksjhzg");

        }

        @Override
        public ZonedDateTime deserialize(JsonParser jp, DeserializationContext context) throws IOException {
            logger.info("HARDCORE: "+jp.toString());
            return ZonedDateTime.now();
        }
    }

    @Bean
    SearchSanitizer createSearchSanitizer(){
        return new SearchSanitizer();
    }


}
