package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.dto.RideCreateRequest;
import com.dehradun.cabbooking.entity.Ride;
import com.dehradun.cabbooking.service.RideService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes REST endpoints for creating and querying rides.
 */
@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     * Creates a new ride using the provided payload.
     *
     * @param request ride creation payload
     * @return response containing the created ride
     */
    @PostMapping
    public ResponseEntity<Ride> createRide(@Valid @RequestBody RideCreateRequest request) {
        Ride saved = rideService.createRide(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Retrieves rides created on the specified date.
     *
     * @param date date to filter by
     * @return rides created on the date
     */
    @GetMapping("/by-date")
    public List<Ride> getRidesByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return rideService.getRidesByCreationDate(date);
    }

    /**
     * Retrieves completed rides for the supplied week.
     *
     * @param weekStart start date of the week
     * @return rides completed within the week
     */
    @GetMapping("/completed")
    public List<Ride> getCompletedRides(
        @RequestParam("weekStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weekStart
    ) {
        return rideService.getCompletedRidesForWeek(weekStart);
    }

    /**
     * Retrieves rides currently in progress for today.
     *
     * @return rides in progress today
     */
    @GetMapping("/in-progress/today")
    public List<Ride> getInProgressRidesForToday() {
        return rideService.getInProgressRidesForToday();
    }

    /**
     * Retrieves rides exceeding the configured thresholds during the provided week.
     *
     * @param weekStart start date of the week
     * @param fareThreshold fare threshold for filtering
     * @return rides meeting the high-value criteria
     */
    @GetMapping("/high-value")
    public List<Ride> getHighValueRides(
        @RequestParam("weekStart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate weekStart,
        @RequestParam("fare") BigDecimal fareThreshold
    ) {
        return rideService.getHighValueRidesForWeek(weekStart, fareThreshold);
    }

    /**
     * Retrieves a ride by identifier.
     *
     * @param rideId ride identifier to fetch
     * @return ride entity for the identifier
     */
    @GetMapping("/{rideId}")
    public Ride getRide(@PathVariable Long rideId) {
        return rideService.getRideById(rideId);
    }

    /**
     * Retrieves rides that used a specific discount code.
     *
     * @param code discount code to filter by
     * @return rides that reference the discount
     */
    @GetMapping("/by-discount/{code}")
    public List<Ride> getRidesByDiscount(@PathVariable String code) {
        return rideService.getRidesByDiscountCode(code);
    }
}
