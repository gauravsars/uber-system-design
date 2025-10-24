package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.dto.CreateRideRequest;
import com.dehradun.cabbooking.entity.Discount;
import com.dehradun.cabbooking.entity.Driver;
import com.dehradun.cabbooking.entity.Location;
import com.dehradun.cabbooking.entity.Ride;
import com.dehradun.cabbooking.entity.User;
import com.dehradun.cabbooking.entity.Vehicle;
import com.dehradun.cabbooking.enums.RideStatus;
import com.dehradun.cabbooking.repository.DriverRepository;
import com.dehradun.cabbooking.repository.LocationRepository;
import com.dehradun.cabbooking.repository.RideRepository;
import com.dehradun.cabbooking.repository.UserRepository;
import com.dehradun.cabbooking.repository.VehicleRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Business service handling ride creation and search flows.
 */
@Service
public class RideService {

    private static final BigDecimal TEN_KM = BigDecimal.valueOf(10);

    private final RideRepository rideRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final LocationRepository locationRepository;
    private final DiscountService discountService;

    /**
     * Constructs the service with the required repositories.
     *
     * @param rideRepository repository handling rides
     * @param userRepository repository providing riders
     * @param driverRepository repository providing drivers
     * @param vehicleRepository repository providing vehicles
     * @param locationRepository repository providing locations
     * @param discountService service exposing discount lookups
     */
    public RideService(RideRepository rideRepository, UserRepository userRepository,
        DriverRepository driverRepository, VehicleRepository vehicleRepository,
        LocationRepository locationRepository, DiscountService discountService) {
        this.rideRepository = rideRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.locationRepository = locationRepository;
        this.discountService = discountService;
    }

    /**
     * Persists a new ride request linking the actors, vehicle, locations, and discounts.
     *
     * @param request ride creation payload
     * @return persisted ride entity
     */
    @Transactional
    public Ride createRide(CreateRideRequest request) {
        User user = userRepository
            .findById(request.getUserId())
            .filter(stored -> !stored.isDeleted())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Driver driver = null;
        if (request.getDriverId() != null) {
            driver = driverRepository
                .findById(request.getDriverId().intValue())
                .filter(stored -> !stored.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found"));
        }

        Vehicle vehicle = null;
        if (request.getVehicleId() != null) {
            vehicle = vehicleRepository
                .findById(request.getVehicleId())
                .filter(stored -> !stored.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle not found"));
        }

        Location pickup = resolveLocation(request.getPickupLocationId(), request.getPickupLatitude(),
            request.getPickupLongitude());
        Location drop = resolveLocation(request.getDropLocationId(), request.getDropLatitude(),
            request.getDropLongitude());

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
        ride.setDeleted(false);
        ride.setCreatedAt(LocalDateTime.now());

        List<Discount> discounts = discountService.getDiscountsByCodes(request.getDiscountCodes());
        ride.setDiscounts(discounts);

        return rideRepository.save(ride);
    }

    /**
     * Loads a ride by identifier ensuring the record is active.
     *
     * @param rideId ride identifier to load
     * @return matching ride entity
     */
    public Ride getRideById(Integer rideId) {
        return rideRepository
            .findById(rideId)
            .filter(ride -> !ride.isDeleted())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride not found"));
    }

    /**
     * Retrieves rides created on the provided date.
     *
     * @param date date representing the creation day
     * @return list of rides created on that day
     */
    public List<Ride> getRidesByCreationDate(LocalDate date) {
        LocalDate targetDate = date != null ? date : LocalDate.now();
        LocalDateTime start = targetDate.atStartOfDay();
        LocalDateTime end = targetDate.atTime(LocalTime.MAX);
        List<Ride> rides = rideRepository.findByDeletedFalseAndCreatedAtBetween(start, end);
        return rides;
    }

    /**
     * Retrieves rides completed during the week containing the provided date.
     *
     * @param dateInWeek date used to determine the week boundaries
     * @return list of rides completed within the week
     */
    public List<Ride> getCompletedRidesForWeek(LocalDate dateInWeek) {
        LocalDate reference = dateInWeek != null ? dateInWeek : LocalDate.now();
        LocalDate startOfWeek = reference.minusDays(reference.getDayOfWeek().getValue() - 1L);
        LocalDateTime start = startOfWeek.atStartOfDay();
        LocalDateTime end = start.plusDays(7).minusNanos(1);
        return rideRepository.findByDeletedFalseAndStatusAndEndTimeBetween(RideStatus.COMPLETED, start, end);
    }

    /**
     * Retrieves rides that are currently in progress and were created today.
     *
     * @return list of in-progress rides for today
     */
    public List<Ride> getInProgressRidesForToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.atTime(LocalTime.MAX);
        return rideRepository.findByDeletedFalseAndStatusInAndCreatedAtBetween(
            EnumSet.of(RideStatus.ACCEPTED, RideStatus.ONGOING), start, end);
    }

    /**
     * Retrieves rides for the week where distance exceeds ten kilometres or fare exceeds the threshold.
     *
     * @param dateInWeek date used to determine the week
     * @param minimumFare minimum fare threshold for filtering
     * @return list of rides matching the high-value criteria
     */
    public List<Ride> getHighValueRidesForWeek(LocalDate dateInWeek, BigDecimal minimumFare) {
        LocalDate reference = dateInWeek != null ? dateInWeek : LocalDate.now();
        LocalDate startOfWeek = reference.minusDays(reference.getDayOfWeek().getValue() - 1L);
        LocalDateTime start = startOfWeek.atStartOfDay();
        LocalDateTime end = start.plusDays(7).minusNanos(1);
        BigDecimal fareThreshold = minimumFare != null ? minimumFare : BigDecimal.ZERO;
        return rideRepository.findRidesByDistanceOrFare(start, end, TEN_KM, fareThreshold);
    }

    /**
     * Retrieves rides that used the supplied discount code.
     *
     * @param code discount code applied on rides
     * @return list of rides utilising the discount
     */
    public List<Ride> getRidesByDiscountCode(String code) {
        return rideRepository.findActiveRidesByDiscountCode(code);
    }

    /**
     * Resolves a location either from an existing identifier or by creating a new record.
     *
     * @param locationId existing location identifier, may be {@code null}
     * @param latitude latitude for new location creation
     * @param longitude longitude for new location creation
     * @return persistent location entity
     */
    private Location resolveLocation(Integer locationId, BigDecimal latitude, BigDecimal longitude) {
        if (locationId != null) {
            return locationRepository
                .findById(locationId)
                .filter(location -> !location.isDeleted())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location not found"));
        }
        if (latitude == null || longitude == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location coordinates missing");
        }
        Location location = new Location();
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        location.setDeleted(false);
        location.setRecordedAt(LocalDateTime.now());
        return locationRepository.save(location);
    }
}
