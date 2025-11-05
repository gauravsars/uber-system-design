package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.RideStatus;
import jakarta.persistence.Converter;

/**
 * Supports case-insensitive storage of {@link RideStatus} values.
 */
@Converter(autoApply = true)
public class RideStatusConverter extends AbstractCaseInsensitiveEnumConverter<RideStatus> {

    /**
     * Instantiates the converter for {@link RideStatus}.
     */
    public RideStatusConverter() {
        super(RideStatus.class);
    }
}
