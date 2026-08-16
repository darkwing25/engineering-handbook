package com.example.customer;

/**
 * Creates customer repositories.
 */
public final class CustomerRepositoryFactory
{
	/**
	 * Prevents construction.
	 */
	private CustomerRepositoryFactory()
	{
	}

	/**
	 * Creates the customer repository.
	 *
	 * @return The customer repository.
	 */
	public static CustomerRepository createCustomerRepository()
	{
		CustomerRepository customerRepository = null;

		customerRepository = new InMemoryCustomerRepository();

		return customerRepository;
	}
}
