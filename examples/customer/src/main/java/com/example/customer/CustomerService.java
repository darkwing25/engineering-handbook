package com.example.customer;

import java.math.BigDecimal;

import com.example.logging.ILogger;
import com.example.logging.LoggerFactory;

/**
 * Provides customer business operations through explicit collaborators.
 */
public class CustomerService
{
	// ########################################################################
	// Constants
	// ########################################################################

	private static final ILogger LOGGER = LoggerFactory.getLogger(CustomerService.class);

	// ########################################################################
	// Attributes
	// ########################################################################

	private CustomerDiscountStrategyFactory m_customerDiscountStrategyFactory;
	private CustomerRepository m_customerRepository;

	// ########################################################################
	// Accessors
	// ########################################################################

	/**
	 * Returns the strategy factory, creating the class-owned default when required.
	 *
	 * @return The strategy factory.
	 */
	final CustomerDiscountStrategyFactory getCustomerDiscountStrategyFactory()
	{
		if (m_customerDiscountStrategyFactory == null)
		{
			m_customerDiscountStrategyFactory = new CustomerDiscountStrategyFactory();
		}

		return m_customerDiscountStrategyFactory;
	}

	/**
	 * Replaces the strategy factory through a controlled package test seam.
	 *
	 * @param p_customerDiscountStrategyFactory The strategy factory.
	 */
	final void setCustomerDiscountStrategyFactory(final CustomerDiscountStrategyFactory p_customerDiscountStrategyFactory)
	{
		if (p_customerDiscountStrategyFactory == null)
		{
			throw new IllegalArgumentException("The customer discount strategy factory is required.");
		}

		m_customerDiscountStrategyFactory = p_customerDiscountStrategyFactory;
	}

	/**
	 * Returns the customer repository.
	 *
	 * @return The customer repository.
	 */
	public CustomerRepository getCustomerRepository()
	{
		if (m_customerRepository == null)
		{
			throw new IllegalStateException("The customer repository has not been configured.");
		}

		return m_customerRepository;
	}

	/**
	 * Replaces the customer repository through a controlled package test seam.
	 *
	 * @param p_customerRepository The customer repository.
	 */
	final void setCustomerRepository(final CustomerRepository p_customerRepository)
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
	 * @param p_customerRepository The required customer repository.
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
	 * @return The customer.
	 * @throws CustomerLoadException Thrown when the customer cannot be loaded.
	 */
	public Customer loadCustomer(final String p_customerId) throws CustomerLoadException
	{
		Customer customer = null;

		LOGGER.info("Loading customer '{}'.", p_customerId);
		customer = getCustomerRepository().loadCustomer(p_customerId);

		if (customer == null)
		{
			throw new CustomerLoadException("Customer '" + p_customerId + "' was not found.", null);
		}

		return customer;
	}

	/**
	 * Calculates the discount for a customer using a strategy selected for this call.
	 *
	 * @param p_customer The customer.
	 * @param p_orderTotal The order total.
	 * @return The calculated discount.
	 */
	public BigDecimal calculateDiscount(final Customer p_customer, final BigDecimal p_orderTotal)
	{
		BigDecimal discount = BigDecimal.ZERO;

		if (p_customer != null)
		{
			CustomerDiscountStrategy strategy = getCustomerDiscountStrategyFactory().createStrategy(p_customer.getCustomerType());

			discount = calculateDiscount(p_orderTotal, strategy);
		}

		return discount;
	}

	// ########################################################################
	// Protected Methods
	// ########################################################################

	// ########################################################################
	// Private Methods
	// ########################################################################

	/**
	 * Calculates a discount through a caller-supplied strategy. Package visibility is an intentional test seam.
	 *
	 * @param p_orderTotal The order total.
	 * @param p_strategy The selected strategy.
	 * @return The calculated discount.
	 */
	final BigDecimal calculateDiscount(final BigDecimal p_orderTotal, final CustomerDiscountStrategy p_strategy)
	{
		BigDecimal discount = BigDecimal.ZERO;

		if (p_strategy == null)
		{
			throw new IllegalArgumentException("The customer discount strategy is required.");
		}
		else
		{
			discount = p_strategy.calculateDiscount(p_orderTotal);
		}

		return discount;
	}

	// ########################################################################
	// Main
	// ########################################################################
}
