package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Vehicle;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for vehicle persistence operations.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    /**
     * Finds a vehicle that is not marked as deleted.
     *
     * @param id identifier of the vehicle
     * @return optional containing the vehicle when active
     */
    Optional<Vehicle> findByIdAndDeletedFalse(Long id);
}
