package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.dto.CreateDriverRequest;
import com.dehradun.cabbooking.entity.Driver;
import com.dehradun.cabbooking.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing driver partner endpoints.
 */
@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    /**
     * Builds the controller with the service dependency.
     *
     * @param driverService business service handling drivers
     */
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    /**
     * Registers a new driver in the marketplace.
     *
     * @param request driver registration payload
     * @return response containing the created driver
     */
    @PostMapping
    public ResponseEntity<Driver> createDriver(@Valid @RequestBody CreateDriverRequest request) {
        Driver created = driverService.createDriver(request);
        return ResponseEntity.status(201).body(created);
    }

    /**
     * Fetches driver details using the identifier.
     *
     * @param driverId identifier of the driver
     * @return driver entity
     */
    @GetMapping("/{driverId}")
    public Driver getDriver(@PathVariable Integer driverId) {
        return driverService.getDriverById(driverId);
    }
}
