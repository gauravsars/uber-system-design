package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.dto.DriverCreateRequest;
import com.dehradun.cabbooking.entity.Driver;
import com.dehradun.cabbooking.entity.Vehicle;
import com.dehradun.cabbooking.enums.DriverStatus;
import com.dehradun.cabbooking.repository.DriverRepository;
import com.dehradun.cabbooking.repository.VehicleRepository;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Coordinates driver onboarding and lookup operations.
 */
@Service
public class DriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public DriverService(DriverRepository driverRepository, VehicleRepository vehicleRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Registers a driver along with their vehicle details.
     *
     * @param request payload describing the driver and vehicle
     * @return persisted driver entity
     */
    @Transactional
    public Driver createDriver(DriverCreateRequest request) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setPhone(request.getPhone());
        driver.setEmail(request.getEmail());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setRating(BigDecimal.ZERO);
        driver.setStatus(DriverStatus.OFFLINE);
        driver.setCreatedAt(OffsetDateTime.now());

        Driver savedDriver = driverRepository.save(driver);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriver(savedDriver);
        vehicle.setVehicleNumber(request.getVehicleNumber());
        vehicle.setModel(request.getVehicleModel());
        vehicle.setType(request.getVehicleType());
        vehicle.setCapacity(request.getCapacity());
        vehicle.setCreatedAt(OffsetDateTime.now());
        vehicleRepository.save(vehicle);

        savedDriver.setVehicle(vehicle);
        return savedDriver;
    }

    /**
     * Retrieves an undeleted driver by identifier.
     *
     * @param driverId identifier of the driver to retrieve
     * @return matching driver entity
     */
    @Transactional(readOnly = true)
    public Driver getDriverById(Long driverId) {
        return driverRepository
            .findByIdAndDeletedFalse(driverId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
    }
}
