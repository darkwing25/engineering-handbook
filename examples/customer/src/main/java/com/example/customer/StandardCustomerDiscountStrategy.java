package com.example.customer;

import java.math.BigDecimal;

/**
 * Calculates discounts for standard customers.
 */
public class StandardCustomerDiscountStrategy implements CustomerDiscountStrategy
{
	@Override
	public BigDecimal calculateDiscount(final BigDecimal p_orderTotal)
	{
		BigDecimal discount = BigDecimal.ZERO;

		return discount;
	}
}
