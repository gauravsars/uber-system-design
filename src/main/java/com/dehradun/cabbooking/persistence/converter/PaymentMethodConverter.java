package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.PaymentMethod;
import jakarta.persistence.Converter;

/**
 * Converts {@link PaymentMethod} enums while ignoring case differences in the database.
 */
@Converter(autoApply = true)
public class PaymentMethodConverter extends AbstractCaseInsensitiveEnumConverter<PaymentMethod> {

    /**
     * Sets up the converter for {@link PaymentMethod}.
     */
    public PaymentMethodConverter() {
        super(PaymentMethod.class);
    }
}
