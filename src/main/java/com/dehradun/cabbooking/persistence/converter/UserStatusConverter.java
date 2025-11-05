package com.dehradun.cabbooking.persistence.converter;

import com.dehradun.cabbooking.enums.UserStatus;
import jakarta.persistence.Converter;

/**
 * Handles case-insensitive persistence of {@link UserStatus} values.
 */
@Converter(autoApply = true)
public class UserStatusConverter extends AbstractCaseInsensitiveEnumConverter<UserStatus> {

    /**
     * Builds a converter that can translate between {@link UserStatus} and the database column.
     */
    public UserStatusConverter() {
        super(UserStatus.class);
    }
}
