package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for payment transactions.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
