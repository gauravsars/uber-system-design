package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for booking lifecycle management.
 */
@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {
}
