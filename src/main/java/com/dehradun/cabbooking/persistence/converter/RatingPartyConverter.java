package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.RatingParty;
import jakarta.persistence.Converter;

/**
 * Facilitates case-insensitive persistence of {@link RatingParty} values.
 */
@Converter(autoApply = true)
public class RatingPartyConverter extends AbstractCaseInsensitiveEnumConverter<RatingParty> {

    /**
     * Configures the converter for {@link RatingParty} enumerations.
     */
    public RatingPartyConverter() {
        super(RatingParty.class);
    }
}
