package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the Dehradun vehicle fleet.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
