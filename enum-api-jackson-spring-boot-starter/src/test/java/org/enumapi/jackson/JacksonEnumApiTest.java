package org.enumapi.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.enumapi.configuration.EnumApiAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class JacksonEnumApiTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        EnumApiAutoConfiguration configuration = new EnumApiAutoConfiguration();
        objectMapper.registerModule(configuration.jsonObjectMapper());
    }

    @Test
    void deserializeKnownEnumConstant() throws JsonProcessingException {
        String knownColor = '"' + KnownColor.RED.toString() + '"';
        AnyColor deserializedColor = objectMapper.readValue(knownColor, AnyColor.class);
        assertSame(deserializedColor, KnownColor.RED);
    }

    @Test
    void deserializeUnknownEnumConstant() throws JsonProcessingException {
        String knownColor = '"' + "ORANGE" + '"';
        AnyColor firstDeserialized = objectMapper.readValue(knownColor, AnyColor.class);
        AnyColor secondDeserialized = objectMapper.readValue(knownColor, AnyColor.class);
        assertSame(firstDeserialized, secondDeserialized);
    }

    @Test
    void deserializeTwoUnknownEnumConstants() throws JsonProcessingException {
        String knownColor = '"' + "GREEN" + '"';
        AnyColor firstDeserialized = objectMapper.readValue(knownColor, AnyColor.class);
        AnyExperience secondDeserialized = objectMapper.readValue(knownColor, AnyExperience.class);
        assertNotSame(firstDeserialized, secondDeserialized);
    }



}
