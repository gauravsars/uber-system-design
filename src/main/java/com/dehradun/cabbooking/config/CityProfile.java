package com.dehradun.cabbooking.config;

/**
 * Immutable descriptor representing the city that the marketplace serves.
 */
public class CityProfile {

    private final String name;
    private final String state;
    private final String country;
    private final String timezone;
    private final String supportEmail;

    /**
     * Builds a city descriptor instance.
     *
     * @param name         friendly name of the city
     * @param state        state in which the city lies
     * @param country      country of operation
     * @param timezone     IANA timezone identifier
     * @param supportEmail support contact for the locale
     */
    public CityProfile(String name, String state, String country, String timezone, String supportEmail) {
        this.name = name;
        this.state = state;
        this.country = country;
        this.timezone = timezone;
        this.supportEmail = supportEmail;
    }

    /**
     * Fetches the display name of the city.
     *
     * @return friendly city name
     */
    public String getName() {
        return name;
    }

    /**
     * Fetches the state in which the city resides.
     *
     * @return Indian state name
     */
    public String getState() {
        return state;
    }

    /**
     * Fetches the operating country.
     *
     * @return country in ISO friendly format
     */
    public String getCountry() {
        return country;
    }

    /**
     * Fetches the timezone of the city.
     *
     * @return IANA timezone identifier
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * Fetches the support email for local riders and drivers.
     *
     * @return support contact email
     */
    public String getSupportEmail() {
        return supportEmail;
    }
}
