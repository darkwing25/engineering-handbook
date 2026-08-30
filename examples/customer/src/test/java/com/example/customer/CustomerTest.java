package com.example.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests every application-visible customer method.
 */
class CustomerTest
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
	 * Tests the constructor happy path with a valid customer identifier.
	 */
	@Test
	void test_constructor()
	{
		// Arrange

		String customerId = "123";

		// Act

		Customer customer = new Customer(customerId);

		// Assert

		assertEquals(customerId, customer.getCustomerId());
	}

	/**
	 * Tests the constructor failure path triggered by a missing customer identifier.
	 */
	@Test
	void test_constructor_missingCustomerId()
	{
		// Arrange

		String customerId = null;
		Executable constructorOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				new Customer(customerId);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, constructorOperation);

		// Assert

		assertEquals("The customer identifier is required.", exception.getMessage());
	}

	/**
	 * Tests the customer-identifier setter happy path with a replacement identifier.
	 */
	@Test
	void test_setCustomerId()
	{
		// Arrange

		Customer customer = new Customer("123");

		// Act

		customer.setCustomerId("456");

		// Assert

		assertEquals("456", customer.getCustomerId());
	}

	/**
	 * Tests the customer-identifier setter failure path triggered by a null identifier.
	 */
	@Test
	void test_setCustomerId_missingCustomerId()
	{
		// Arrange

		Customer customer = new Customer("123");
		Executable setOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				customer.setCustomerId(null);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, setOperation);

		// Assert

		assertEquals("The customer identifier is required.", exception.getMessage());
	}

	/**
	 * Tests the name-accessor edge path before a name has been supplied.
	 */
	@Test
	void test_getName_default()
	{
		// Arrange

		Customer customer = new Customer("123");

		// Act

		String name = customer.getName();

		// Assert

		assertEquals("", name);
	}

	/**
	 * Tests the name-setter happy path with a supplied customer name.
	 */
	@Test
	void test_setName()
	{
		// Arrange

		Customer customer = new Customer("123");

		// Act

		customer.setName("Ada Lovelace");

		// Assert

		assertEquals("Ada Lovelace", customer.getName());
	}

	/**
	 * Tests the customer-type accessor edge path before a type has been supplied.
	 */
	@Test
	void test_getCustomerType_default()
	{
		// Arrange

		Customer customer = new Customer("123");

		// Act

		CustomerType customerType = customer.getCustomerType();

		// Assert

		assertEquals(CustomerType.STANDARD, customerType);
	}

	/**
	 * Tests the customer-type setter happy path with a premium customer type.
	 */
	@Test
	void test_setCustomerType()
	{
		// Arrange

		Customer customer = new Customer("123");

		// Act

		customer.setCustomerType(CustomerType.PREMIUM);

		// Assert

		assertEquals(CustomerType.PREMIUM, customer.getCustomerType());
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
