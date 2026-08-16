package com.example.customer;

/**
 * Creates customer discount strategies.
 */
public final class CustomerDiscountStrategyFactory
{
	/**
	 * Prevents construction.
	 */
	private CustomerDiscountStrategyFactory()
	{
	}

	/**
	 * Creates the discount strategy.
	 *
	 * @param p_customerType The customer type.
	 *
	 * @return The discount strategy.
	 */
	public static CustomerDiscountStrategy createStrategy(final CustomerType p_customerType)
	{
		CustomerDiscountStrategy strategy = null;

		if (p_customerType == CustomerType.PREMIUM)
		{
			strategy = new PremiumCustomerDiscountStrategy();
		}
		else
		{
			strategy = new StandardCustomerDiscountStrategy();
		}

		return strategy;
	}
}
