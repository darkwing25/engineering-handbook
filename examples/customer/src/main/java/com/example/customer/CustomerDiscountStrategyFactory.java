package com.example.customer;

/**
 * Creates customer discount strategies.
 */
public class CustomerDiscountStrategyFactory
{
	// ########################################################################
	// Constants
	// ########################################################################

	// ########################################################################
	// Attributes
	// ########################################################################

	// ########################################################################
	// Accessors
	// ########################################################################

	// ########################################################################
	// Constructors
	// ########################################################################

	/**
	 * Creates a customer discount strategy factory.
	 */
	public CustomerDiscountStrategyFactory()
	{
	}

	// ########################################################################
	// Public Methods
	// ########################################################################

	/**
	 * Creates the discount strategy.
	 *
	 * @param p_customerType The customer type.
	 * @return The discount strategy.
	 */
	public CustomerDiscountStrategy createStrategy(final CustomerType p_customerType)
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

	// ########################################################################
	// Protected Methods
	// ########################################################################

	// ########################################################################
	// Private Methods
	// ########################################################################

	// ########################################################################
	// Main
	// ########################################################################
}
