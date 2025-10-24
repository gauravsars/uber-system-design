package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a cab booking placed within the city limits of Dehradun.
 */
@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ride_id")
    private Integer rideId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    @JsonManagedReference("ride-driver")
    private Driver driver;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @JsonManagedReference("ride-vehicle")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "pickup_location_id")
    @JsonIgnore
    private Location pickupLocation;

    @ManyToOne
    @JoinColumn(name = "drop_location_id")
    @JsonIgnore
    private Location dropLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private RideStatus status = RideStatus.REQUESTED;

    @Column(name = "fare", precision = 10, scale = 2)
    private BigDecimal fare;

    @Column(name = "distance_km", precision = 6, scale = 2)
    private BigDecimal distanceKm;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;


    @ManyToMany
    @JoinTable(name = "ride_discounts",
        joinColumns = @JoinColumn(name = "ride_id"),
        inverseJoinColumns = @JoinColumn(name = "discount_id"))
    @JsonManagedReference("ride-discount")
    private List<Discount> discounts = new ArrayList<>();

    @OneToMany(mappedBy = "ride")
    @JsonManagedReference("ride-ratings")
    private List<Rating> ratings = new ArrayList<>();

    @OneToOne(mappedBy = "ride")
    @JsonManagedReference("ride-payment")
    private Payment payment;

    /**
     * Returns the identifier for the ride record.
     *
     * @return ride primary key
     */
    public Integer getRideId() {
        return rideId;
    }

    /**
     * Sets the identifier for the ride record.
     *
     * @param rideId database generated ride identifier
     */
    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    /**
     * Retrieves the rider who requested the ride.
     *
     * @return user entity associated with the ride
     */
    public User getUser() {
        return user;
    }

    /**
     * Associates the rider who requested the ride.
     *
     * @param user user entity linked to the ride
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the driver assigned to the ride.
     *
     * @return driver entity handling the ride
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Assigns the driver responsible for the ride.
     *
     * @param driver driver entity handling the trip
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Provides the vehicle used for the ride.
     *
     * @return vehicle entity assigned to the ride
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Sets the vehicle used for the ride.
     *
     * @param vehicle vehicle entity assigned to the ride
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Obtains the pickup location for the ride.
     *
     * @return location entity representing the pickup spot
     */
    public Location getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Defines the pickup location for the ride.
     *
     * @param pickupLocation location entity representing the pickup spot
     */
    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    /**
     * Obtains the drop location for the ride.
     *
     * @return location entity representing the drop spot
     */
    public Location getDropLocation() {
        return dropLocation;
    }

    /**
     * Defines the drop location for the ride.
     *
     * @param dropLocation location entity representing the drop spot
     */
    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    /**
     * Retrieves the current lifecycle status of the ride.
     *
     * @return ride status enumeration
     */
    public RideStatus getStatus() {
        return status;
    }

    /**
     * Updates the lifecycle status of the ride.
     *
     * @param status new status to assign
     */
    public void setStatus(RideStatus status) {
        this.status = status;
    }

    /**
     * Provides the total fare charged for the ride.
     *
     * @return fare amount in rupees
     */
    public BigDecimal getFare() {
        return fare;
    }

    /**
     * Sets the total fare charged for the ride.
     *
     * @param fare fare amount in rupees
     */
    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    /**
     * Returns the travelled distance in kilometers.
     *
     * @return distance metric for the ride
     */
    public BigDecimal getDistanceKm() {
        return distanceKm;
    }

    /**
     * Stores the travelled distance in kilometers.
     *
     * @param distanceKm measured distance for the ride
     */
    public void setDistanceKm(BigDecimal distanceKm) {
        this.distanceKm = distanceKm;
    }

    /**
     * Provides the start timestamp of the ride.
     *
     * @return ride start timestamp in server time
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start timestamp of the ride.
     *
     * @param startTime ride start timestamp in server time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Provides the end timestamp of the ride.
     *
     * @return ride completion timestamp in server time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end timestamp of the ride.
     *
     * @param endTime ride completion timestamp in server time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Indicates whether the ride has been soft deleted.
     *
     * @return true when the ride should be hidden
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the ride as logically removed without deletion.
     *
     * @param deleted deletion state flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Exposes the creation timestamp of the ride record.
     *
     * @return ride creation timestamp tracked in the database
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Stores the creation timestamp of the ride record.
     *
     * @param createdAt ride creation timestamp tracked in the database
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Lists the discounts applied to this ride.
     *
     * @return discounts associated with the ride
     */
    public List<Discount> getDiscounts() {
        return discounts;
    }

    /**
     * Assigns discounts applied to this ride.
     *
     * @param discounts discount collection used for the ride
     */
    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    /**
     * Returns the ratings captured for this ride.
     *
     * @return list of rating entities
     */
    public List<Rating> getRatings() {
        return ratings;
    }

    /**
     * Stores the ratings captured for this ride.
     *
     * @param ratings list of rating entities
     */
    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    /**
     * Provides the payment transaction associated with the ride.
     *
     * @return payment entity linked to the ride
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Sets the payment transaction associated with the ride.
     *
     * @param payment payment entity linked to the ride
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
