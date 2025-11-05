package com.dehradun.cabbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * Composite key joining a ride with an applied discount.
 */
@Embeddable
public class RideDiscountId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ride_id")
    private Long rideId;

    @Column(name = "discount_id")
    private Long discountId;

    /**
     * Retrieves the ride identifier component.
     *
     * @return ride identifier
     */
    public Long getRideId() {
        return rideId;
    }

    /**
     * Sets the ride identifier component.
     *
     * @param rideId ride identifier to store
     */
    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    /**
     * Retrieves the discount identifier component.
     *
     * @return discount identifier
     */
    public Long getDiscountId() {
        return discountId;
    }

    /**
     * Sets the discount identifier component.
     *
     * @param discountId discount identifier to store
     */
    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RideDiscountId that)) {
            return false;
        }
        return Objects.equals(rideId, that.rideId) && Objects.equals(discountId, that.discountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rideId, discountId);
    }
}
