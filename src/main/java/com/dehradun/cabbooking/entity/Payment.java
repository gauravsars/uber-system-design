package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.PaymentMethod;
import com.dehradun.cabbooking.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Tracks payments initiated for rides in Dehradun.
 */
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    @OneToOne
    @JoinColumn(name = "ride_id", unique = true)
    @JsonBackReference("ride-payment")
    private Ride ride;

    @Column(name = "amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", length = 20)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    /**
     * Returns the identifier of the payment record.
     *
     * @return payment primary key
     */
    public Integer getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the identifier of the payment record.
     *
     * @param paymentId payment primary key
     */
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Retrieves the ride associated with the payment.
     *
     * @return ride entity linked to the payment
     */
    public Ride getRide() {
        return ride;
    }

    /**
     * Associates the ride with the payment.
     *
     * @param ride ride entity linked to the payment
     */
    public void setRide(Ride ride) {
        this.ride = ride;
    }

    /**
     * Provides the amount charged for the ride.
     *
     * @return payment amount in rupees
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount charged for the ride.
     *
     * @param amount payment amount in rupees
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Returns the method used for payment.
     *
     * @return payment method enumeration
     */
    public PaymentMethod getMethod() {
        return method;
    }

    /**
     * Sets the method used for payment.
     *
     * @param method payment method enumeration
     */
    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    /**
     * Provides the status of the payment.
     *
     * @return payment status enumeration
     */
    public PaymentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the payment.
     *
     * @param status payment status enumeration
     */
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    /**
     * Returns the creation timestamp recorded when the payment was initiated of the payment record.
     *
     * @return creation timestamp recorded when the payment was initiated
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp recorded when the payment was initiated of the payment record.
     *
     * @param createdAt creation timestamp recorded when the payment was initiated
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Indicates whether the payment is soft deleted.
     *
     * @return true when payment should be ignored
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the payment as logically removed.
     *
     * @param deleted logical deletion flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
