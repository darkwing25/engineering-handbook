package com.example.customer;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory customer repository used by examples and tests.
 */
public class InMemoryCustomerRepository implements CustomerRepository
{
	// ########################################################################
	// Constants
	// ########################################################################

	// ########################################################################
	// Attributes
	// ########################################################################

	private Map<String, Customer> m_customersById;

	// ########################################################################
	// Accessors
	// ########################################################################

	/**
	 * Returns the customer map.
	 *
	 * @return The customer map.
	 */
	Map<String, Customer> getCustomersById()
	{
		if (m_customersById == null)
		{
			m_customersById = new HashMap<>();
		}

		return m_customersById;
	}

	// ########################################################################
	// Constructors
	// ########################################################################

	/**
	 * Creates an in-memory customer repository.
	 */
	public InMemoryCustomerRepository()
	{
	}

	// ########################################################################
	// Public Methods
	// ########################################################################

	@Override
	public Customer loadCustomer(final String p_customerId) throws CustomerLoadException
	{
		Customer customer = null;

		try
		{
			customer = getCustomersById().get(p_customerId);
		}
		catch (RuntimeException p_exception)
		{
			// Convert the repository failure into an application-specific checked exception.
			throw new CustomerLoadException("Unable to load customer '" + p_customerId + "'.", p_exception);
		}

		return customer;
	}

	/**
	 * Saves a customer.
	 *
	 * @param p_customer The customer.
	 */
	public void saveCustomer(final Customer p_customer)
	{
		getCustomersById().put(p_customer.getCustomerId(), p_customer);
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
