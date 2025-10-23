package com.dehradun.cabbooking.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Request payload to create a ride and associate it with the required actors.
 */
public class CreateRideRequest {

    @NotNull
    private Long userId;

    private Long driverId;

    private Integer vehicleId;

    private Integer pickupLocationId;

    private Integer dropLocationId;

    private BigDecimal pickupLatitude;

    private BigDecimal pickupLongitude;

    private BigDecimal dropLatitude;

    private BigDecimal dropLongitude;

    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal fare;

    @DecimalMin(value = "0.00", inclusive = false)
    private BigDecimal distanceKm;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private List<String> discountCodes;

    /**
     * Provides the identifier of the rider requesting the ride.
     *
     * @return rider primary key
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the identifier of the rider requesting the ride.
     *
     * @param userId rider primary key
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Returns the identifier of the assigned driver.
     *
     * @return driver primary key
     */
    public Long getDriverId() {
        return driverId;
    }

    /**
     * Sets the identifier of the assigned driver.
     *
     * @param driverId driver primary key
     */
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    /**
     * Retrieves the identifier of the vehicle involved in the ride.
     *
     * @return vehicle primary key
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the identifier of the vehicle involved in the ride.
     *
     * @param vehicleId vehicle primary key
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Gets an existing pickup location identifier if available.
     *
     * @return pickup location identifier
     */
    public Integer getPickupLocationId() {
        return pickupLocationId;
    }

    /**
     * Sets an existing pickup location identifier if available.
     *
     * @param pickupLocationId pickup location identifier
     */
    public void setPickupLocationId(Integer pickupLocationId) {
        this.pickupLocationId = pickupLocationId;
    }

    /**
     * Gets an existing drop location identifier if available.
     *
     * @return drop location identifier
     */
    public Integer getDropLocationId() {
        return dropLocationId;
    }

    /**
     * Sets an existing drop location identifier if available.
     *
     * @param dropLocationId drop location identifier
     */
    public void setDropLocationId(Integer dropLocationId) {
        this.dropLocationId = dropLocationId;
    }

    /**
     * Provides the latitude for a newly recorded pickup location.
     *
     * @return pickup latitude
     */
    public BigDecimal getPickupLatitude() {
        return pickupLatitude;
    }

    /**
     * Sets the latitude for a newly recorded pickup location.
     *
     * @param pickupLatitude pickup latitude
     */
    public void setPickupLatitude(BigDecimal pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    /**
     * Provides the longitude for a newly recorded pickup location.
     *
     * @return pickup longitude
     */
    public BigDecimal getPickupLongitude() {
        return pickupLongitude;
    }

    /**
     * Sets the longitude for a newly recorded pickup location.
     *
     * @param pickupLongitude pickup longitude
     */
    public void setPickupLongitude(BigDecimal pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    /**
     * Provides the latitude for a newly recorded drop location.
     *
     * @return drop latitude
     */
    public BigDecimal getDropLatitude() {
        return dropLatitude;
    }

    /**
     * Sets the latitude for a newly recorded drop location.
     *
     * @param dropLatitude drop latitude
     */
    public void setDropLatitude(BigDecimal dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    /**
     * Provides the longitude for a newly recorded drop location.
     *
     * @return drop longitude
     */
    public BigDecimal getDropLongitude() {
        return dropLongitude;
    }

    /**
     * Sets the longitude for a newly recorded drop location.
     *
     * @param dropLongitude drop longitude
     */
    public void setDropLongitude(BigDecimal dropLongitude) {
        this.dropLongitude = dropLongitude;
    }

    /**
     * Returns the total fare charged for the ride.
     *
     * @return fare amount
     */
    public BigDecimal getFare() {
        return fare;
    }

    /**
     * Sets the total fare charged for the ride.
     *
     * @param fare fare amount
     */
    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    /**
     * Returns the total trip distance covered.
     *
     * @return distance in kilometres
     */
    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    /**
     * Sets the total trip distance covered.
     *
     * @param distanceKm distance in kilometres
     */
    public void setDistanceKm(BigDecimal distanceKm) {
        this.distanceKm = distanceKm;
    }

    /**
     * Provides the timestamp when the ride started.
     *
     * @return start timestamp
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the timestamp when the ride started.
     *
     * @param startTime start timestamp
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Provides the timestamp when the ride ended.
     *
     * @return end timestamp
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the timestamp when the ride ended.
     *
     * @param endTime end timestamp
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves discount codes that should be linked to the ride.
     *
     * @return list of discount code strings
     */
    public List<String> getDiscountCodes() {
        return discountCodes;
    }

    /**
     * Sets discount codes that should be linked to the ride.
     *
     * @param discountCodes list of discount code strings
     */
    public void setDiscountCodes(List<String> discountCodes) {
        this.discountCodes = discountCodes;
    }
}
