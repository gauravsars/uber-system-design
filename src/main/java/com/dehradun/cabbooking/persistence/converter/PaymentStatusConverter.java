package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.PaymentStatus;
import jakarta.persistence.Converter;

/**
 * Manages bidirectional conversion of {@link PaymentStatus} values.
 */
@Converter(autoApply = true)
public class PaymentStatusConverter extends AbstractCaseInsensitiveEnumConverter<PaymentStatus> {

    /**
     * Builds the converter for {@link PaymentStatus} enums.
     */
    public PaymentStatusConverter() {
        super(PaymentStatus.class);
    }
}
