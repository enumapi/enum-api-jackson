package com.evilcorp.enumapi.jackson;

import com.evilcorp.enumapi.EnumProxy;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.module.SimpleSerializers;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Arrays;

public class GenericEnumSerializers extends SimpleSerializers {
    @Override
    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
        final Class<?> rawClass = type.getRawClass();
        if (Arrays.asList(rawClass.getInterfaces()).contains(EnumProxy.class)) {
            return new ToStringSerializer();
        }
        return super.findSerializer(config, type, beanDesc);
    }
}
