package com.evilcorp.enumdemo.domain.jackson;

import com.fasterxml.jackson.annotation.JsonValue;

final class GenericEnumDto {

    @JsonValue
    private final String value;

    @Override
    public final String toString() {
        return value;
    }

    public GenericEnumDto(String value) {
        this.value = value;
    }

    public final String getValue() {
        return value;
    }
}
