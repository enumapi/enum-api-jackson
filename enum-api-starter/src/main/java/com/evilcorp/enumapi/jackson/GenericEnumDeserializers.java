package com.evilcorp.enumapi.jackson;

import com.evilcorp.enumapi.EnumApi;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;

public class GenericEnumDeserializers extends SimpleDeserializers {
    @Override
    public JsonDeserializer<?> findBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        final Class<?> enumInterface = type.getRawClass();
        if (enumInterface.isAnnotationPresent(EnumApi.class)) {
            return new GenericEnumDeserializer(enumInterface.getAnnotation(EnumApi.class).enumClass(), enumInterface);
        }
        return super.findBeanDeserializer(type, config, beanDesc);
    }
}
