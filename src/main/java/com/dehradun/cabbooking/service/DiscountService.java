package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.entity.Discount;
import com.dehradun.cabbooking.repository.DiscountRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides business logic for querying discount availability.
 */
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    /**
     * Retrieves discounts active on the provided date.
     *
     * @param referenceDate date used to evaluate availability
     * @return list of currently valid discounts
     */
    @Transactional(readOnly = true)
    public List<Discount> getAvailableDiscounts(LocalDate referenceDate) {
        return discountRepository.findActiveDiscounts(referenceDate);
    }
}
