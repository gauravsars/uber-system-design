package com.dehradun.cabbooking.persistence.converter;

import jakarta.persistence.AttributeConverter;

/**
 * Provides reusable conversion logic for case-insensitive enum to string mappings.
 *
 * @param <E> concrete enumeration type handled by the converter
 */
public abstract class AbstractCaseInsensitiveEnumConverter<E extends Enum<E>> implements AttributeConverter<E, String> {

    private final Class<E> enumClass;

    /**
     * Creates a new converter for the provided enumeration type.
     *
     * @param enumClass enumeration class that should be mapped case-insensitively
     */
    protected AbstractCaseInsensitiveEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        for (E constant : enumClass.getEnumConstants()) {
            if (constant.name().equalsIgnoreCase(dbData)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Unknown value '" + dbData + "' for enum " + enumClass.getSimpleName());
    }
}
