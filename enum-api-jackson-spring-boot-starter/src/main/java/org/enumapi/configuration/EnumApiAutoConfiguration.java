package org.enumapi.configuration;

import org.enumapi.jackson.GenericEnumDeserializers;
import org.enumapi.jackson.GenericEnumSerializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.Module;
import org.enumapi.annotations.EnumApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Auto configuration for a started, which just adds
 * {@link GenericEnumDeserializers} and {@link GenericEnumSerializers}
 * to {@link com.fasterxml.jackson.databind.ObjectMapper}.
 * That makes it possible to have special serialization and
 * deserialization rules for interfaces, marked with {@link EnumApi}
 */
@Configuration
public class EnumApiAutoConfiguration {
    @Bean
    public Module jsonObjectMapper() {
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.setDeserializers(new GenericEnumDeserializers());
        simpleModule.setSerializers(new GenericEnumSerializers());
        return simpleModule;
    }
}
