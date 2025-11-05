package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.DriverStatus;
import jakarta.persistence.Converter;

/**
 * Ensures {@link DriverStatus} values are mapped without case sensitivity issues.
 */
@Converter(autoApply = true)
public class DriverStatusConverter extends AbstractCaseInsensitiveEnumConverter<DriverStatus> {

    /**
     * Creates a converter for {@link DriverStatus} enums.
     */
    public DriverStatusConverter() {
        super(DriverStatus.class);
    }
}
