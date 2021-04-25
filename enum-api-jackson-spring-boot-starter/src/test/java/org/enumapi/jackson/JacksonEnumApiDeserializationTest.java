package org.enumapi.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.enumapi.configuration.EnumApiAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class JacksonEnumApiDeserializationTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        EnumApiAutoConfiguration configuration = new EnumApiAutoConfiguration();
        objectMapper.registerModule(configuration.jsonObjectMapper());
    }

    /**
     * The scenario here is that we have a microservice, which accepts
     * {@link AnyColor}.
     * If an external service sends one of the values
     * contained in {@link KnownColor}, it should be deserialized to
     * corresponding enum constant.
     * For example RED should be deserialized to {@link KnownColor#RED}
     */
    @Test
    void deserializeKnownEnumConstant() throws JsonProcessingException {
        String knownColor = '"' + KnownColor.RED.toString() + '"';
        AnyColor deserializedColor = objectMapper.readValue(knownColor, AnyColor.class);
        assertSame(deserializedColor, KnownColor.RED);
    }

    /**
     * The scenario here is that we have a microservice, which accepts
     * {@link AnyColor}.
     * If an external service sends a value not contained in {@link KnownColor},
     * it should be deserialized to an object implementing {@link AnyColor}.
     * </p>
     * All later attempts to deserialize that same value should return that
     * same object, the first deserialization attempt created.
     */
    @Test
    void deserializeUnknownEnumConstant() throws JsonProcessingException {
        String knownColor = '"' + "ORANGE" + '"';
        AnyColor firstDeserialized = objectMapper.readValue(knownColor, AnyColor.class);
        AnyColor secondDeserialized = objectMapper.readValue(knownColor, AnyColor.class);
        assertSame(firstDeserialized, secondDeserialized);
    }

    /**
     * The scenario here is that we have a microservice, which accepts
     * {@link AnyColor} and {@link AnyExperience}.
     * If an external service sends a value not contained in {@link KnownColor},
     * it should be deserialized to an object implementing {@link AnyColor}.
     * </p>
     * If an external service later sends the same value a value where {@link AnyExperience}
     * is expected, and that value is not contained in {@link KnownExperience},
     * then it should be deserialized to another object.
     * </p>
     * For example, if an external service first sends GREEN where {@link AnyColor}
     * is expected, and then sends GREEN, where {@link AnyExperience} is expected,
     * then two distinct objects should be returned.
     * </p>
     * This seems to be obvious, but still should be tested, because implementation
     * tends to have this exact detail wrong.
     */
    @Test
    void deserializeTwoUnknownEnumConstants() throws JsonProcessingException {
        String knownColor = '"' + "GREEN" + '"';
        AnyColor firstDeserialized = objectMapper.readValue(knownColor, AnyColor.class);
        AnyExperience secondDeserialized = objectMapper.readValue(knownColor, AnyExperience.class);
        //This is obvious, because there are no classes, which implement both
        //AnyColor and AnyExperience. But I still leave the assertion, to clarify
        //test intent
        //noinspection AssertBetweenInconvertibleTypes
        assertNotSame(firstDeserialized, secondDeserialized);
    }



}
