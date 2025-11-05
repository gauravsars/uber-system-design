package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.dto.RideCreateRequest;
import com.dehradun.cabbooking.entity.Discount;
import com.dehradun.cabbooking.entity.Driver;
import com.dehradun.cabbooking.entity.Location;
import com.dehradun.cabbooking.entity.Ride;
import com.dehradun.cabbooking.entity.RideDiscount;
import com.dehradun.cabbooking.entity.RideDiscountId;
import com.dehradun.cabbooking.entity.User;
import com.dehradun.cabbooking.entity.Vehicle;
import com.dehradun.cabbooking.enums.RideStatus;
import com.dehradun.cabbooking.repository.DiscountRepository;
import com.dehradun.cabbooking.repository.DriverRepository;
import com.dehradun.cabbooking.repository.LocationRepository;
import com.dehradun.cabbooking.repository.RideDiscountRepository;
import com.dehradun.cabbooking.repository.RideRepository;
import com.dehradun.cabbooking.repository.UserRepository;
import com.dehradun.cabbooking.repository.VehicleRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Orchestrates ride creation and complex retrieval queries.
 */
@Service
public class RideService {

    private static final ZoneId INDIA_ZONE = ZoneId.of("Asia/Kolkata");
    private static final BigDecimal DISTANCE_THRESHOLD_KM = new BigDecimal("10");

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    private final DiscountRepository discountRepository;
    private final RideDiscountRepository rideDiscountRepository;

    public RideService(
        RideRepository rideRepository,
        UserRepository userRepository,
        DriverRepository driverRepository,
        VehicleRepository vehicleRepository,
        LocationRepository locationRepository,
        DiscountRepository discountRepository,
        RideDiscountRepository rideDiscountRepository
    ) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
        this.discountRepository = discountRepository;
        this.rideDiscountRepository = rideDiscountRepository;
    }

    /**
     * Creates a ride and persists linked locations and discounts.
     *
     * @param request payload describing the ride to create
     * @return persisted ride entity
     */
    @Transactional
    public Ride createRide(RideCreateRequest request) {
        User user = userRepository
            .findByIdAndDeletedFalse(request.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Driver driver = driverRepository
            .findByIdAndDeletedFalse(request.getDriverId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
        Vehicle vehicle = vehicleRepository
            .findByIdAndDeletedFalse(request.getVehicleId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));

        Location pickup = buildLocation(request.getPickupLatitude(), request.getPickupLongitude());
        Location drop = buildLocation(request.getDropLatitude(), request.getDropLongitude());
        locationRepository.save(pickup);
        locationRepository.save(drop);

        Ride ride = new Ride();
        ride.setUser(user);
        ride.setDriver(driver);
        ride.setVehicle(vehicle);
        ride.setPickupLocation(pickup);
        ride.setDropLocation(drop);
        ride.setFare(request.getFare());
        ride.setDistanceKm(request.getDistanceKm());
        ride.setStartTime(request.getStartTime());
        ride.setEndTime(request.getEndTime());
        ride.setStatus(RideStatus.REQUESTED);
        ride.setCreatedAt(OffsetDateTime.now());
        Ride savedRide = rideRepository.save(ride);

        if (request.getDiscountCodes() != null) {
            request.getDiscountCodes().forEach(code -> attachDiscount(savedRide, code));
        }

        return savedRide;
    }

    /**
     * Retrieves rides created on the supplied date.
     *
     * @param date date to filter by
     * @return rides created during that day
     */
    @Transactional(readOnly = true)
    public List<Ride> getRidesByCreationDate(LocalDate date) {
        OffsetDateTime start = date.atStartOfDay(INDIA_ZONE).toOffsetDateTime();
        OffsetDateTime end = start.plusDays(1);
        return rideRepository.findCreatedBetween(start, end);
    }

    /**
     * Retrieves completed rides for the week beginning with the provided date.
     *
     * @param weekStart first day of the week to evaluate
     * @return rides completed within the given week
     */
    @Transactional(readOnly = true)
    public List<Ride> getCompletedRidesForWeek(LocalDate weekStart) {
        OffsetDateTime start = weekStart.atStartOfDay(INDIA_ZONE).toOffsetDateTime();
        OffsetDateTime end = start.plusWeeks(1);
        return rideRepository.findByStatusWithin(RideStatus.COMPLETED, start, end);
    }

    /**
     * Retrieves rides considered in progress for the current day.
     *
     * @return rides whose status denotes active execution today
     */
    @Transactional(readOnly = true)
    public List<Ride> getInProgressRidesForToday() {
        LocalDate today = LocalDate.now(INDIA_ZONE);
        OffsetDateTime start = today.atStartOfDay(INDIA_ZONE).toOffsetDateTime();
        OffsetDateTime end = start.plusDays(1);
        return rideRepository.findInProgress(List.of(RideStatus.ACCEPTED, RideStatus.ONGOING), start, end);
    }

    /**
     * Retrieves rides surpassing either the distance or fare threshold within a week.
     *
     * @param weekStart first day of the week
     * @param fareThreshold fare amount to compare against
     * @return rides meeting the high value criteria
     */
    @Transactional(readOnly = true)
    public List<Ride> getHighValueRidesForWeek(LocalDate weekStart, BigDecimal fareThreshold) {
        OffsetDateTime start = weekStart.atStartOfDay(INDIA_ZONE).toOffsetDateTime();
        OffsetDateTime end = start.plusWeeks(1);
        return rideRepository.findHighValueRides(start, end, DISTANCE_THRESHOLD_KM, fareThreshold);
    }

    /**
     * Retrieves a ride by identifier ensuring it is not deleted.
     *
     * @param rideId ride identifier to fetch
     * @return matching ride entity
     */
    @Transactional(readOnly = true)
    public Ride getRideById(Long rideId) {
        return rideRepository
            .findByIdAndDeletedFalse(rideId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride not found"));
    }

    /**
     * Retrieves rides that used the specified discount code.
     *
     * @param code discount code applied to rides
     * @return rides referencing the provided discount code
     */
    @Transactional(readOnly = true)
    public List<Ride> getRidesByDiscountCode(String code) {
        return rideDiscountRepository
            .findByDiscount_CodeIgnoreCase(code)
            .stream()
            .map(RideDiscount::getRide)
            .collect(Collectors.toList());
    }

    /**
     * Builds a location entity from provided coordinates.
     *
     * @param latitude latitude component
     * @param longitude longitude component
     * @return location entity ready for persistence
     */
    private Location buildLocation(Double latitude, Double longitude) {
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setRecordedAt(OffsetDateTime.now());
        location.setDeleted(false);
        return location;
    }

    /**
     * Links a discount code to the ride if it exists.
     *
     * @param ride ride receiving the discount
     * @param discountCode code identifying the discount
     */
    private void attachDiscount(Ride ride, String discountCode) {
        Discount discount = discountRepository
            .findByCodeIgnoreCaseAndDeletedFalse(discountCode)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Discount not found: " + discountCode));
        LocalDate today = LocalDate.now(INDIA_ZONE);
        if ((discount.getValidFrom() != null && discount.getValidFrom().isAfter(today))
            || (discount.getValidTo() != null && discount.getValidTo().isBefore(today))
            || discount.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discount is not active: " + discountCode);
        }
        RideDiscount association = new RideDiscount();
        association.setRide(ride);
        association.setDiscount(discount);
        RideDiscountId id = new RideDiscountId();
        id.setRideId(ride.getId());
        id.setDiscountId(discount.getId());
        association.setId(id);
        rideDiscountRepository.save(association);
    }
}
