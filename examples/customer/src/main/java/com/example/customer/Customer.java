package com.example.customer;

/**
 * Represents a customer.
 */
public class Customer
{
	// ########################################################################
	// Constants
	// ########################################################################

	// ########################################################################
	// Attributes
	// ########################################################################

	private String m_customerId;
	private String m_name;
	private CustomerType m_customerType;

	// ########################################################################
	// Accessors
	// ########################################################################

	/**
	 * Returns the customer identifier.
	 *
	 * @return The customer identifier.
	 */
	public String getCustomerId()
	{
		String customerId = m_customerId;

		if (customerId == null)
		{
			throw new IllegalStateException("The customer identifier has not been configured.");
		}

		return customerId;
	}

	/**
	 * Sets the customer identifier during controlled construction or testing.
	 *
	 * @param p_customerId The customer identifier.
	 */
	final void setCustomerId(final String p_customerId)
	{
		if (p_customerId == null)
		{
			throw new IllegalArgumentException("The customer identifier is required.");
		}

		m_customerId = p_customerId;
	}

	/**
	 * Returns the customer name.
	 *
	 * @return The customer name, or an empty string when no name is configured.
	 */
	public String getName()
	{
		String name = m_name;

		if (name == null)
		{
			name = "";
		}

		return name;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param p_name The customer name.
	 */
	public void setName(final String p_name)
	{
		m_name = p_name;
	}

	/**
	 * Returns the customer type.
	 *
	 * @return The customer type, defaulting to standard.
	 */
	public CustomerType getCustomerType()
	{
		CustomerType customerType = m_customerType;

		if (customerType == null)
		{
			customerType = CustomerType.STANDARD;
		}

		return customerType;
	}

	/**
	 * Sets the customer type.
	 *
	 * @param p_customerType The customer type.
	 */
	public void setCustomerType(final CustomerType p_customerType)
	{
		m_customerType = p_customerType;
	}

	// ########################################################################
	// Constructors
	// ########################################################################

	/**
	 * Creates a customer.
	 *
	 * @param p_customerId The customer identifier.
	 */
	public Customer(final String p_customerId)
	{
		setCustomerId(p_customerId);
	}

	// ########################################################################
	// Public Methods
	// ########################################################################

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
