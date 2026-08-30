package com.example.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests the in-memory repository, pure repository factory, and repository exception.
 */
class CustomerRepositoryTest
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

	// ########################################################################
	// Public Methods
	// ########################################################################

	/**
	 * Tests the in-memory repository constructor happy path.
	 */
	@Test
	void test_repositoryConstructor()
	{
		// Arrange

		InMemoryCustomerRepository repository = null;

		// Act

		repository = new InMemoryCustomerRepository();

		// Assert

		assertInstanceOf(InMemoryCustomerRepository.class, repository);
	}

	/**
	 * Tests the lazy-map accessor path and verifies that repeated calls retain the same map.
	 */
	@Test
	void test_getCustomersById()
	{
		// Arrange

		InMemoryCustomerRepository repository = new InMemoryCustomerRepository();

		// Act

		Map<String, Customer> firstCustomers = repository.getCustomersById();
		Map<String, Customer> secondCustomers = repository.getCustomersById();

		// Assert

		assertSame(firstCustomers, secondCustomers);
		assertEquals(0, firstCustomers.size());
	}

	/**
	 * Tests the repository save-and-load happy path for an existing customer.
	 *
	 * @throws CustomerLoadException Thrown when the repository cannot load the saved customer.
	 */
	@Test
	void test_saveAndLoadCustomer() throws CustomerLoadException
	{
		// Arrange

		InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
		Customer customer = new Customer("123");

		// Act

		repository.saveCustomer(customer);
		Customer loadedCustomer = repository.loadCustomer("123");

		// Assert

		assertSame(customer, loadedCustomer);
	}

	/**
	 * Tests the load edge path when no customer matches the requested identifier.
	 *
	 * @throws CustomerLoadException Thrown when the repository cannot complete the lookup.
	 */
	@Test
	void test_loadCustomer_missingCustomer() throws CustomerLoadException
	{
		// Arrange

		InMemoryCustomerRepository repository = new InMemoryCustomerRepository();

		// Act

		Customer customer = repository.loadCustomer("missing");

		// Assert

		assertNull(customer);
	}

	/**
	 * Tests the save failure path triggered by a missing customer.
	 */
	@Test
	void test_saveCustomer_missingCustomer()
	{
		// Arrange

		InMemoryCustomerRepository repository = new InMemoryCustomerRepository();
		Executable saveOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				repository.saveCustomer(null);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, saveOperation);

		// Assert

		assertEquals("The customer is required.", exception.getMessage());
	}

	/**
	 * Tests the pure repository-factory happy path and its concrete implementation selection.
	 */
	@Test
	void test_createCustomerRepository()
	{
		// Arrange

		CustomerRepository repository = null;

		// Act

		repository = CustomerRepositoryFactory.createCustomerRepository();

		// Assert

		assertInstanceOf(InMemoryCustomerRepository.class, repository);
	}

	/**
	 * Tests the load-exception constructor happy path by preserving its message and cause.
	 */
	@Test
	void test_customerLoadExceptionConstructor()
	{
		// Arrange

		RuntimeException cause = new RuntimeException("Storage unavailable.");

		// Act

		CustomerLoadException exception = new CustomerLoadException("Unable to load.", cause);

		// Assert

		assertEquals("Unable to load.", exception.getMessage());
		assertSame(cause, exception.getCause());
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
