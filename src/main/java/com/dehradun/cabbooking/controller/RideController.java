package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.dto.CreateRideRequest;
import com.dehradun.cabbooking.entity.Ride;
import com.dehradun.cabbooking.service.RideService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing ride lifecycle endpoints.
 */
@RestController
@RequestMapping("/api/rides")
public class RideController {

    private final RideService rideService;

    /**
     * Builds the controller with the ride service dependency.
     *
     * @param rideService business service orchestrating rides
     */
    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    /**
     * Creates a new ride for the provided actors and locations.
     *
     * @param request ride creation payload
     * @return response containing the persisted ride
     */
    @PostMapping
    public ResponseEntity<Ride> createRide(@Valid @RequestBody CreateRideRequest request) {
        Ride created = rideService.createRide(request);
        return ResponseEntity.status(201).body(created);
    }

    /**
     * Fetches the details of a ride using the identifier.
     *
     * @param rideId ride identifier
     * @return ride entity
     */
    @GetMapping("/{rideId}")
    public Ride getRide(@PathVariable Integer rideId) {
        return rideService.getRideById(rideId);
    }

    /**
     * Fetches rides that were created on the provided date.
     *
     * @param date date to filter by, defaults to today when not supplied
     * @return list of rides created on the date
     */
    @GetMapping("/by-date")
    public List<Ride> getRidesByDate(@RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return rideService.getRidesByCreationDate(date);
    }

    /**
     * Fetches rides that were completed during the week containing the provided date.
     *
     * @param date date to determine the week, defaults to current date when absent
     * @return list of completed rides
     */
    @GetMapping("/completed-week")
    public List<Ride> getCompletedRidesForWeek(@RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return rideService.getCompletedRidesForWeek(date);
    }

    /**
     * Fetches rides that are currently in progress for the current day.
     *
     * @return list of in-progress rides
     */
    @GetMapping("/in-progress/today")
    public List<Ride> getInProgressRidesForToday() {
        return rideService.getInProgressRidesForToday();
    }

    /**
     * Fetches rides whose distance exceeds ten kilometres or fare exceeds the supplied threshold.
     *
     * @param date date to determine the week, defaults to current date when absent
     * @param minFare minimum fare threshold to filter high-value rides
     * @return list of rides meeting the criteria
     */
    @GetMapping("/high-value-week")
    public List<Ride> getHighValueRides(@RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam(required = false) BigDecimal minFare) {
        return rideService.getHighValueRidesForWeek(date, minFare);
    }

    /**
     * Fetches rides that used the supplied discount code.
     *
     * @param code discount code applied to rides
     * @return list of rides utilising the discount
     */
    @GetMapping("/discount/{code}")
    public List<Ride> getRidesByDiscountCode(@PathVariable String code) {
        return rideService.getRidesByDiscountCode(code);
    }
}
