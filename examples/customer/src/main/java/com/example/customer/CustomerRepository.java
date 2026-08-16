package com.example.customer;

/**
 * Defines customer persistence operations.
 */
public interface CustomerRepository
{
	/**
	 * Loads a customer.
	 *
	 * @param p_customerId The customer identifier.
	 *
	 * @return The customer, or null when it does not exist.
	 *
	 * @throws CustomerLoadException Thrown when the customer cannot be loaded.
	 */
	Customer loadCustomer(final String p_customerId) throws CustomerLoadException;
}
