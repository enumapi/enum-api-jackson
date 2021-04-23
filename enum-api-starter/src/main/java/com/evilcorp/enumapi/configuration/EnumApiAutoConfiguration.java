package com.evilcorp.enumapi.configuration;

import com.evilcorp.enumapi.jackson.GenericEnumDeserializers;
import com.evilcorp.enumapi.jackson.GenericEnumSerializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configuration for a started, which just adds
 * {@link GenericEnumDeserializers} and {@link GenericEnumSerializers}
 * to {@link com.fasterxml.jackson.databind.ObjectMapper}.
 * That makes it possible to have special serialization and
 * deserialization rules for interfaces, marked with {@link com.evilcorp.annotations.EnumApi}
 */
@Configuration
public class EnumApiAutoConfiguration {
    @Bean
    public Module jsonObjectMapper() {
        final var simpleModule = new SimpleModule();
        simpleModule.setDeserializers(new GenericEnumDeserializers());
        simpleModule.setSerializers(new GenericEnumSerializers());
        return simpleModule;
    }
}
