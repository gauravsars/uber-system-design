package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Captures the details of a single ride transaction between a user and driver.
 */
@Entity
@Table(name = "rides")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "vehicle"})
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "pickup_location_id")
    private Location pickupLocation;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "drop_location_id")
    private Location dropLocation;

    @Column(name = "status", nullable = false)
    private RideStatus status = RideStatus.REQUESTED;

    @Column(name = "fare", precision = 10, scale = 2)
    private BigDecimal fare;

    @Column(name = "distance_km", precision = 6, scale = 2)
    private BigDecimal distanceKm;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    /**
     * Returns the unique identifier of the ride.
     *
     * @return ride primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier, intended for persistence frameworks.
     *
     * @param id ride identifier from the database
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Supplies the user who requested the ride.
     *
     * @return user entity reference
     */
    public User getUser() {
        return user;
    }

    /**
     * Associates a user with the ride.
     *
     * @param user user entity to link
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Provides the driver assigned to fulfil the ride.
     *
     * @return driver entity reference
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Associates a driver with the ride request.
     *
     * @param driver driver entity to link
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Returns the vehicle that serviced the ride.
     *
     * @return vehicle entity reference
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Links a vehicle to the ride record.
     *
     * @param vehicle vehicle entity to associate
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Retrieves the pickup location entity.
     *
     * @return pickup location reference
     */
    public Location getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Associates a pickup location with the ride.
     *
     * @param pickupLocation location entity to assign
     */
    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * Fetches the drop location entity.
     *
     * @return drop location reference
     */
    public Location getDropLocation() {
        return dropLocation;
    }

    /**
     * Associates a drop location with the ride.
     *
     * @param dropLocation location entity to assign
     */
    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    /**
     * Reveals the lifecycle status of the ride.
     *
     * @return ride status enum
     */
    public RideStatus getStatus() {
        return status;
    }

    /**
     * Updates the lifecycle status of the ride.
     *
     * @param status ride status to persist
     */
    public void setStatus(RideStatus status) {
        this.status = status;
    }

    /**
     * Provides the fare charged for the ride.
     *
     * @return fare amount
     */
    public BigDecimal getFare() {
        return fare;
    }

    /**
     * Sets the fare amount for the ride.
     *
     * @param fare fare value to store
     */
    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    /**
     * Returns the travelled distance in kilometres.
     *
     * @return distance travelled
     */
    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    /**
     * Updates the recorded distance for the ride.
     *
     * @param distanceKm distance value to persist
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
     * Sets the start timestamp for the ride.
     *
     * @param startTime timestamp to record
     */
    public void setStartTime(OffsetDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Returns the timestamp when the ride finished.
     *
     * @return ride end time
     */
    public OffsetDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end timestamp for the ride.
     *
     * @param endTime timestamp to record
     */
    public void setEndTime(OffsetDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Indicates whether the ride record has been soft deleted.
     *
     * @return true when deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the ride record as deleted or active.
     *
     * @param deleted delete flag to store
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Supplies the timestamp when the ride entry was created.
     *
     * @return creation timestamp
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp on the entity instance.
     *
     * @param createdAt creation timestamp to persist
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
