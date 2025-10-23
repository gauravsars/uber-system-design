package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.dto.CreateDriverRequest;
import com.dehradun.cabbooking.entity.Driver;
import com.dehradun.cabbooking.enums.DriverStatus;
import com.dehradun.cabbooking.repository.DriverRepository;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Business service orchestrating driver lifecycle actions.
 */
@Service
public class DriverService {

    private final DriverRepository driverRepository;

    /**
     * Instantiates the service with the required repository dependency.
     *
     * @param driverRepository repository handling driver persistence
     */
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    /**
     * Registers a new driver using the supplied payload.
     *
     * @param request request payload describing the driver details
     * @return persisted driver entity
     */
    @Transactional
    public Driver createDriver(CreateDriverRequest request) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setPhone(request.getPhone());
        driver.setEmail(request.getEmail());
        driver.setLicenseNumber(request.getLicenseNumber());
        driver.setStatus(DriverStatus.OFFLINE);
        driver.setDeleted(false);
        driver.setCreatedAt(LocalDateTime.now());
        return driverRepository.save(driver);
    }

    /**
     * Loads a driver profile by identifier ensuring the record is active.
     *
     * @param driverId identifier of the desired driver
     * @return driver entity from the database
     */
    public Driver getDriverById(Integer driverId) {
        return driverRepository
            .findById(driverId)
            .filter(driver -> !driver.isDeleted())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
    }
}
