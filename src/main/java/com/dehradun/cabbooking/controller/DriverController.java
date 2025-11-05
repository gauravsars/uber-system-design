package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.dto.DriverCreateRequest;
import com.dehradun.cabbooking.entity.Driver;
import com.dehradun.cabbooking.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes REST endpoints for driver onboarding and lookup.
 */
@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Registers a new driver.
     *
     * @param request payload describing the driver and vehicle
     * @return response containing the created driver
     */
    @PostMapping
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody DriverCreateRequest request) {
        Driver saved = driverService.createDriver(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Fetches a driver by identifier.
     *
     * @param driverId identifier of the driver to retrieve
     * @return driver entity for the identifier
     */
    @GetMapping("/{driverId}")
    public Driver getDriver(@PathVariable Long driverId) {
        return driverService.getDriverById(driverId);
    }
}
