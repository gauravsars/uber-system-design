package com.dehradun.cabbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

/**
 * Join entity representing the association between rides and applied discounts.
 */
@Entity
@Table(name = "ride_discounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RideDiscount {

    @EmbeddedId
    private RideDiscountId id = new RideDiscountId();

    @MapsId("rideId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "ride_id")
    private Ride ride;

    @MapsId("discountId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "discount_id")
    private Discount discount;

    /**
     * Provides the composite primary key for the association.
     *
     * @return composite identifier value
     */
    public RideDiscountId getId() {
        return id;
    }

    /**
     * Sets the composite primary key for the association.
     *
     * @param id composite identifier to store
     */
    public void setId(RideDiscountId id) {
        this.id = id;
    }

    /**
     * Returns the ride to which the discount was applied.
     *
     * @return ride entity reference
     */
    public Ride getRide() {
        return ride;
    }

    /**
     * Associates a ride with this discount application.
     *
     * @param ride ride entity to link
     */
    public void setRide(Ride ride) {
        this.ride = ride;
    }

    /**
     * Provides the discount used on the ride.
     *
     * @return discount entity reference
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * Associates a discount with this ride.
     *
     * @param discount discount entity to link
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

}
