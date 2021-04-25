package org.enumapi.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.enumapi.configuration.EnumApiAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Here we test how Jackson serializes interfaces, marked with
 * {@link org.enumapi.annotations.EnumApi} annotation.
 * </p>
 * Strictly speaking deserialization is also tested, because
 * we have to test how Jackson serializes objects, which were
 * created through deserialization.
 * </p>
 * We assume, that deserialization works perfectly, because it
 * is tested in {@link JacksonEnumApiDeserializationTest}.
 */
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

    /**
     * The scenario here is that we have a microservice, which returns
     * {@link AnyColor}.
     * The test checks, that in case an object of class {@link KnownColor}
     * is sent, it is serialized correctly.
     */
    @Test
    void serializeKnownColor() throws JsonProcessingException {
        String serializedBlueColor = objectMapper.writeValueAsString(blueColor);
        assertEquals("\"BLUE\"", serializedBlueColor);
    }

    /**
     * The scenario here is that we have a microservice, which accepts
     * {@link AnyColor} and then sends it somewhere else.
     * </p>
     * The test checks, that a value, not contained in {@link KnownColor},
     * sent by an external service, goes through deserialization and
     * then is correctly serialized.
     * </p>
     * For example an external service sent VIOLET and then, when
     * deserialized object is returned, it is serialized as VIOLET again.
     */
    @Test
    void serializeUnknownColor() throws JsonProcessingException {
        String serializedVioletColor = objectMapper.writeValueAsString(unknownColorViolet);
        assertEquals("\"VIOLET\"", serializedVioletColor);
    }
}
