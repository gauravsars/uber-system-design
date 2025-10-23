package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.entity.Discount;
import com.dehradun.cabbooking.repository.DiscountRepository;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Business service exposing discount search operations.
 */
@Service
public class DiscountService {

    private final DiscountRepository discountRepository;

    /**
     * Creates the service with the repository dependency.
     *
     * @param discountRepository data access layer for discounts
     */
    public DiscountService(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    /**
     * Loads all discounts that are currently valid in the city.
     *
     * @return list of active discounts
     */
    public List<Discount> getAvailableDiscounts() {
        return discountRepository.findActiveDiscounts(LocalDate.now());
    }

    /**
     * Retrieves active discounts matching the provided codes.
     *
     * @param codes discount codes to resolve
     * @return list of matching discounts, or an empty list when none supplied
     */
    public List<Discount> getDiscountsByCodes(Collection<String> codes) {
        if (codes == null || codes.isEmpty()) {
            return Collections.emptyList();
        }
        return discountRepository.findByCodeInAndDeletedFalse(codes);
    }

    /**
     * Finds a single discount by code when it is active.
     *
     * @param code discount code to lookup
     * @return discount when active, otherwise {@code null}
     */
    public Discount getDiscountByCode(String code) {
        if (code == null || code.isBlank()) {
            return null;
        }
        return discountRepository.findByCodeAndDeletedFalse(code).orElse(null);
    }
}
