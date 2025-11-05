package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.VehicleType;
import jakarta.persistence.Converter;

/**
 * Maps {@link VehicleType} enums to their persistent representations and back.
 */
@Converter(autoApply = true)
public class VehicleTypeConverter extends AbstractCaseInsensitiveEnumConverter<VehicleType> {

    /**
     * Creates a converter for {@link VehicleType} handling.
     */
    public VehicleTypeConverter() {
        super(VehicleType.class);
    }
}
