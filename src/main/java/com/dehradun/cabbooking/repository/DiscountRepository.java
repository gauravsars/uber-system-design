package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for ride discount promotions.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
