package com.ride2go.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ride2go.time.TimeZoneNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BruteForceZonedDateTimeDeserializer extends StdDeserializer<ZonedDateTime> {
    private final Logger logger = LoggerFactory.getLogger(BruteForceZonedDateTimeDeserializer.class);
    public static final String FORMAT2 ="yyyy-MM-dd'T'HH:mmZ";
    public static final String FORMAT ="yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public BruteForceZonedDateTimeDeserializer() {
        this(null);
    }

    public BruteForceZonedDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ZonedDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        final String stringTime = node.textValue();
        return parse(stringTime);
    }

    public ZonedDateTime parse(final String input){
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        try {

            logger.info("BruteForceZonedDateTimeDeserializer: input="+input);
            ZonedDateTime.ofInstant(Instant.parse(input), ZoneId.of(TimeZoneNames.BERLN));
            logger.info("BruteForceZonedDateTimeDeserializer: time="+zonedDateTime.toString());
        }catch (Exception e){
            logger.error("BruteForceZonedDateTimeDeserializer: unable to parse "+input+" into ZonedDateTime with Format "+FORMAT);
        }
        return zonedDateTime;
    }

}