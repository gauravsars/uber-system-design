package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.config.CityProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Provides information about the single city that the platform currently serves.
 */
@Service
public class CityProfileService {

    private final CityProfile cityProfile;

    /**
     * Constructs the service with metadata about Dehradun sourced from configuration.
     *
     * @param cityName     configured name of the city
     * @param stateName    configured state of operation
     * @param countryName  configured country of operation
     * @param timezone     configured timezone identifier
     * @param supportEmail configured contact email address
     */
    public CityProfileService(
        @Value("${city.name:Dehradun}") String cityName,
        @Value("${city.state:Uttarakhand}") String stateName,
        @Value("${city.country:India}") String countryName,
        @Value("${city.timezone:Asia/Kolkata}") String timezone,
        @Value("${city.supportEmail:support@dooncabs.example}") String supportEmail
    ) {
        this.cityProfile = new CityProfile(cityName, stateName, countryName, timezone, supportEmail);
    }

    /**
     * Returns the city profile describing Dehradun deployment specifics.
     *
     * @return immutable city profile value object
     */
    public CityProfile getOperatingCity() {
        return cityProfile;
    }
}
