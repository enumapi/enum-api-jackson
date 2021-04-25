package org.enumapi.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.enumapi.configuration.EnumApiAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class JacksonEnumApiSerializationTest {
    ObjectMapper objectMapper = new ObjectMapper();
    AnyColor blueColor = KnownColor.BLUE;
    AnyColor unknownColorViolet;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        EnumApiAutoConfiguration configuration = new EnumApiAutoConfiguration();
        objectMapper.registerModule(configuration.jsonObjectMapper());
        unknownColorViolet = objectMapper.readValue("\"VIOLET\"", AnyColor.class);
    }

    @Test
    void serializeKnownColor() throws JsonProcessingException {
        String serializedBlueColor = objectMapper.writeValueAsString(blueColor);
        assertEquals("\"BLUE\"", serializedBlueColor);
    }

    @Test
    void serializeUnknownColor() throws JsonProcessingException {
        String serializedVioletColor = objectMapper.writeValueAsString(unknownColorViolet);
        assertEquals("\"VIOLET\"", serializedVioletColor);
    }
}
