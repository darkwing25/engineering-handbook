package com.example.customer;

import java.math.BigDecimal;

import com.example.logging.ILogger;
import com.example.logging.LoggerFactory;

/**
 * Provides customer business operations.
 */
public class CustomerService
{
	// ########################################################################
	// Constants
	// ########################################################################

	private static final ILogger LOGGER =
		LoggerFactory.getLogger(CustomerService.class);

	// ########################################################################
	// Attributes
	// ########################################################################

	private CustomerRepository m_customerRepository;

	// ########################################################################
	// Accessors
	// ########################################################################

	/**
	 * Returns the customer repository.
	 *
	 * @return The customer repository.
	 */
	public CustomerRepository getCustomerRepository()
	{
		CustomerRepository customerRepository = m_customerRepository;

		if (customerRepository == null)
		{
			throw new IllegalStateException("The customer repository has not been configured.");
		}

		return customerRepository;
	}

	/**
	 * Sets the customer repository.
	 *
	 * @param p_customerRepository The customer repository.
	 */
	void setCustomerRepository(final CustomerRepository p_customerRepository)
	{
		if (p_customerRepository == null)
		{
			throw new IllegalArgumentException("The customer repository is required.");
		}

		m_customerRepository = p_customerRepository;
	}

	// ########################################################################
	// Constructors
	// ########################################################################

	/**
	 * Creates a customer service.
	 *
	 * @param p_customerRepository The customer repository.
	 */
	public CustomerService(final CustomerRepository p_customerRepository)
	{
		setCustomerRepository(p_customerRepository);
	}

	// ########################################################################
	// Public Methods
	// ########################################################################

	/**
	 * Loads a customer.
	 *
	 * @param p_customerId The customer identifier.
	 *
	 * @return The customer.
	 *
	 * @throws CustomerLoadException Thrown when the customer cannot be loaded.
	 */
	public Customer loadCustomer(final String p_customerId) throws CustomerLoadException
	{
		Customer customer = null;

		try
		{
			LOGGER.info("Loading customer '{}'.", p_customerId);

			customer = getCustomerRepository().loadCustomer(p_customerId);

			if (customer == null)
			{
				throw new CustomerLoadException("Customer '" + p_customerId + "' was not found.", null);
			}
		}
		catch (CustomerLoadException p_exception)
		{
			// Preserve the customer-load failure while adding a traceable log message.
			LOGGER.error("Unable to load customer. Exception: {}", p_exception.toString());

			throw p_exception;
		}

		return customer;
	}

	/**
	 * Calculates the discount for a customer.
	 *
	 * @param p_customer The customer.
	 * @param p_orderTotal The order total.
	 *
	 * @return The calculated discount.
	 */
	public BigDecimal calculateDiscount(final Customer p_customer, final BigDecimal p_orderTotal)
	{
		BigDecimal discount = BigDecimal.ZERO;
		CustomerDiscountStrategy strategy = null;

		if (p_customer != null)
		{
			strategy = CustomerDiscountStrategyFactory.createStrategy(p_customer.getCustomerType());
			discount = strategy.calculateDiscount(p_orderTotal);
		}

		return discount;
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
