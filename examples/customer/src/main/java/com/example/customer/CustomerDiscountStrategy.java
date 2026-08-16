package com.example.customer;

import java.math.BigDecimal;

/**
 * Calculates a customer discount.
 */
public interface CustomerDiscountStrategy
{
	/**
	 * Calculates the discount.
	 *
	 * @param p_orderTotal The order total.
	 *
	 * @return The discount.
	 */
	BigDecimal calculateDiscount(final BigDecimal p_orderTotal);
}
