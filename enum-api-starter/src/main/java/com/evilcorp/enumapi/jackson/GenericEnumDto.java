package com.evilcorp.enumapi.jackson;

final class GenericEnumDto {

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
