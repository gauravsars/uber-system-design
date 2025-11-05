package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.PaymentMethod;
import com.dehradun.cabbooking.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Records payment transactions for completed rides.
 */
@Entity
@Table(name = "payments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "ride_id", unique = true)
    private Ride ride;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "method", length = 20)
    private PaymentMethod method;

    @Column(name = "status", length = 20, nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    /**
     * Returns the unique identifier for the payment record.
     *
     * @return payment primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier value, generally used by persistence frameworks.
     *
     * @param id identifier assigned by the database
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Provides the ride for which the payment was made.
     *
     * @return ride entity reference
     */
    public Ride getRide() {
        return ride;
    }

    /**
     * Associates a ride with the payment record.
     *
     * @param ride ride entity to link
     */
    public void setRide(Ride ride) {
        this.ride = ride;
    }

    /**
     * Returns the amount paid for the ride.
     *
     * @return payment amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the payment amount for the ride.
     *
     * @param amount payment amount to store
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Provides the method used to complete the payment.
     *
     * @return payment method enum
     */
    public PaymentMethod getMethod() {
        return method;
    }

    /**
     * Sets the method used to complete the payment.
     *
     * @param method payment method to persist
     */
    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    /**
     * Returns the processing status of the payment.
     *
     * @return payment status enum
     */
    public PaymentStatus getStatus() {
        return status;
    }

    /**
     * Updates the processing status of the payment.
     *
     * @param status payment status to persist
     */
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    /**
     * Provides the timestamp when the payment record was created.
     *
     * @return creation timestamp
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp for the payment record.
     *
     * @param createdAt timestamp to store
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Indicates whether the payment has been soft deleted.
     *
     * @return true when deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the payment as deleted or restores it.
     *
     * @param deleted delete flag to persist
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
