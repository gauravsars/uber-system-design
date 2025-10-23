package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing driver data.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
