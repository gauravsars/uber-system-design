package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.config.CityProfile;
import com.dehradun.cabbooking.service.CityProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing localized metadata about Dehradun.
 */
@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityProfileService cityProfileService;

    /**
     * Creates the controller with the service dependency.
     *
     * @param cityProfileService service providing the city descriptor
     */
    public CityController(CityProfileService cityProfileService) {
        this.cityProfileService = cityProfileService;
    }

    /**
     * Returns the metadata describing the Dehradun marketplace configuration.
     *
     * @return city profile response body
     */
    @GetMapping
    public CityProfile getCityProfile() {
        return cityProfileService.getOperatingCity();
    }
}
