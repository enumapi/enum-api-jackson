package com.evilcorp.enumapi.jackson;

import com.evilcorp.enumapi.EnumProxy;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GenericEnumDeserializer extends JsonDeserializer<Object> {

    private static final Map<String, Object> enumMap = new ConcurrentHashMap<>();
    private final Class<?> enumClass;
    private final Class<?> enumInterface;
    private final Map<String, ? extends Enum<?>> enumValues;

    public GenericEnumDeserializer(Class<?> enumClass, Class<?> enumInterface) {
        this.enumClass = enumClass;
        this.enumInterface = enumInterface;
        this.enumValues = Arrays.stream(enumClass.getEnumConstants())
                .map(en -> ((Enum<?>) en))
                .collect(Collectors.toMap(Enum::name, Function.identity()));
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final var value = jsonParser.getValueAsString();
        final var enumValue = enumValues.get(value);
        if (enumValue != null) {
            return enumValue;
        }
        return enumMap.computeIfAbsent(value + enumClass.getName(),
                str -> Proxy.newProxyInstance(
                        enumClass.getClassLoader(),
                        new Class[]{enumInterface, EnumProxy.class},
                        new GenericEnumInvocationHandler(new GenericEnumDto(value)))
        );
    }
}
