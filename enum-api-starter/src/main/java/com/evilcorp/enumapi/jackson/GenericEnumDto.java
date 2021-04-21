package com.evilcorp.enumapi.jackson;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericEnumDto that = (GenericEnumDto) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
