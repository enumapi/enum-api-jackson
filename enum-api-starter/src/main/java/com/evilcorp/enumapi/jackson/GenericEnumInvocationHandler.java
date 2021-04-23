package com.evilcorp.enumapi.jackson;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Invocation handler, backing Proxy, which wraps {@link GenericEnumDto}
 * so that it would implement an interface marked with {@link com.evilcorp.annotations.EnumApi}
 * annotation
 */
class GenericEnumInvocationHandler implements InvocationHandler {

    private final GenericEnumDto dto;

    public GenericEnumInvocationHandler(GenericEnumDto dto) {
        this.dto = dto;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(dto, args);
    }
}
