package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.RideDiscount;
import com.dehradun.cabbooking.entity.RideDiscountId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository managing the ride-discount join table.
 */
@Repository
public interface RideDiscountRepository extends JpaRepository<RideDiscount, RideDiscountId> {

    /**
     * Finds ride-discount associations by discount code.
     *
     * @param code discount code to match
     * @return associations referencing the provided discount code
     */
    List<RideDiscount> findByDiscount_CodeIgnoreCase(String code);
}
