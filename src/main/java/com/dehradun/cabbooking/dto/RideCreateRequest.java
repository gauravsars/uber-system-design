package com.dehradun.cabbooking.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

/**
 * Payload describing information needed to create a ride entry.
 */
public class RideCreateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long driverId;

    @NotNull
    private Long vehicleId;

    @NotNull
    private Double pickupLatitude;

    @NotNull
    private Double pickupLongitude;

    @NotNull
    private Double dropLatitude;

    @NotNull
    private Double dropLongitude;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal fare;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal distanceKm;

    private OffsetDateTime startTime;

    private OffsetDateTime endTime;

    private List<String> discountCodes;

    /**
     * Returns the identifier of the user requesting the ride.
     *
     * @return user identifier
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the identifier of the user requesting the ride.
     *
     * @param userId user identifier to store
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Returns the identifier of the assigned driver.
     *
     * @return driver identifier
     */
    public Long getDriverId() {
        return driverId;
    }

    /**
     * Sets the identifier of the assigned driver.
     *
     * @param driverId driver identifier to store
     */
    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    /**
     * Returns the identifier of the vehicle assigned to the ride.
     *
     * @return vehicle identifier
     */
    public Long getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets the identifier of the vehicle assigned to the ride.
     *
     * @param vehicleId vehicle identifier to store
     */
    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Provides the pickup latitude coordinate.
     *
     * @return pickup latitude
     */
    public Double getPickupLatitude() {
        return pickupLatitude;
    }

    /**
     * Sets the pickup latitude coordinate.
     *
     * @param pickupLatitude latitude to store
     */
    public void setPickupLatitude(Double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }

    /**
     * Provides the pickup longitude coordinate.
     *
     * @return pickup longitude
     */
    public Double getPickupLongitude() {
        return pickupLongitude;
    }

    /**
     * Sets the pickup longitude coordinate.
     *
     * @param pickupLongitude longitude to store
     */
    public void setPickupLongitude(Double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }

    /**
     * Provides the drop latitude coordinate.
     *
     * @return drop latitude
     */
    public Double getDropLatitude() {
        return dropLatitude;
    }

    /**
     * Sets the drop latitude coordinate.
     *
     * @param dropLatitude latitude to store
     */
    public void setDropLatitude(Double dropLatitude) {
        this.dropLatitude = dropLatitude;
    }

    /**
     * Provides the drop longitude coordinate.
     *
     * @return drop longitude
     */
    public Double getDropLongitude() {
        return dropLongitude;
    }

    /**
     * Sets the drop longitude coordinate.
     *
     * @param dropLongitude longitude to store
     */
    public void setDropLongitude(Double dropLongitude) {
        this.dropLongitude = dropLongitude;
    }

    /**
     * Returns the fare to be charged for the ride.
     *
     * @return ride fare
     */
    public BigDecimal getFare() {
        return fare;
    }

    /**
     * Sets the fare to be charged for the ride.
     *
     * @param fare ride fare to store
     */
    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    /**
     * Returns the ride distance in kilometres.
     *
     * @return distance travelled
     */
    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    /**
     * Sets the ride distance in kilometres.
     *
     * @param distanceKm distance value to store
     */
    public void setDistanceKm(BigDecimal distanceKm) {
        this.distanceKm = distanceKm;
    }

    /**
     * Provides the timestamp when the ride started.
     *
     * @return ride start time
     */
    public OffsetDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the timestamp when the ride started.
     *
     * @param startTime ride start time to store
     */
    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Provides the timestamp when the ride ended.
     *
     * @return ride end time
     */
    public OffsetDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the timestamp when the ride ended.
     *
     * @param endTime ride end time to store
     */
    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Returns discount codes to attach to the ride.
     *
     * @return list of discount codes
     */
    public List<String> getDiscountCodes() {
        return discountCodes;
    }

    /**
     * Sets discount codes to attach to the ride.
     *
     * @param discountCodes discount codes to store
     */
    public void setDiscountCodes(List<String> discountCodes) {
        this.discountCodes = discountCodes;
    }
}
