package com.evilcorp.enumapi;

import com.evilcorp.enumapi.jackson.GenericEnumDeserializers;
import com.evilcorp.enumapi.jackson.GenericEnumSerializers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JsonConfig {
    @Bean
    public ObjectMapper jsonObjectMapper() {
        final var simpleModule = new SimpleModule();
        simpleModule.setDeserializers(new GenericEnumDeserializers());
        simpleModule.setSerializers(new GenericEnumSerializers());
        return Jackson2ObjectMapperBuilder.json().modules(simpleModule).build();
    }
}
