package org.enumapi.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks interface as an interface, which is meant to be used in API where
 * usually enum is used.
 * Use this interface to receive any string value, and then compare it to
 * other such values with == operator, as you would do with enums.
 * If used to send something to external service, this interface only
 * restricts what developer may explicitly specify with enum,
 * specified in enumClass parameter.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnumApi {
    /**
     * Enum, implementing interface on which this annotation is put
     */
    Class<? extends Enum<?>> enumClass();
}
