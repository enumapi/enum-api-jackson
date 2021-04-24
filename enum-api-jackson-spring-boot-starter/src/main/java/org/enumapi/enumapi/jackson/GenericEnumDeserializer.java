package org.enumapi.enumapi.jackson;

import org.enumapi.annotations.EnumApi;
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

/**
 * Jackson Deserializer, used to deserialize a string to an interface,
 * marked with EnumApi annotation.
 * Always returns the same object for the same input string.
 * If enum, specified in {@link EnumApi#enumClass()} parameter
 * has a constant, matching input string, then this enum constant
 * is returned.
 * Otherwise an object implementing the interface is returned.
 */
public class GenericEnumDeserializer extends JsonDeserializer<Object> {

    private static final Map<String, Object> enumMap = new ConcurrentHashMap<>();
    private final Class<?> enumClass;
    private final Class<?> enumInterface;
    private final Map<String, ? extends Enum<?>> enumValues;

    /**
     * This constructor is used in {@link GenericEnumDeserializers}, where
     * it's parameters are available
     * @param enumClass enum, implementing the interface
     * @param enumInterface interface
     */
    public GenericEnumDeserializer(Class<?> enumClass, Class<?> enumInterface) {
        this.enumClass = enumClass;
        this.enumInterface = enumInterface;
        this.enumValues = Arrays.stream(enumClass.getEnumConstants())
                .map(en -> ((Enum<?>) en))
                .collect(Collectors.toMap(Enum::name, Function.identity()));
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final String value = jsonParser.getValueAsString();
        final Object enumValue = enumValues.get(value);
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
