package com.example.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests customer service behaviour.
 */
public class CustomerServiceTest
{
	private Customer m_customer;
	private CustomerRepository m_customerRepository;
	private CustomerService m_customerService;

	@BeforeEach
	public void setUp()
	{
		m_customer = new Customer("123");
		m_customer.setName("Ada Lovelace");
		m_customer.setCustomerType(CustomerType.PREMIUM);

		m_customerRepository = mock(CustomerRepository.class);
		m_customerService = new CustomerService(m_customerRepository);
	}

	/**
	 * Tests loading an existing customer.
	 *
	 * @throws CustomerLoadException Thrown when the customer cannot be loaded.
	 */
	@Test
	public void test_loadCustomer() throws CustomerLoadException
	{
		// Arrange

		when(m_customerRepository.loadCustomer("123")).thenReturn(m_customer);

		// Act

		Customer actualCustomer = m_customerService.loadCustomer("123");

		// Assert

		verify(m_customerRepository).loadCustomer("123");
		assertEquals("123", actualCustomer.getCustomerId());
		assertEquals("Ada Lovelace", actualCustomer.getName());
	}

	/**
	 * Tests loading a missing customer.
	 *
	 * @throws CustomerLoadException Thrown when the customer cannot be loaded.
	 */
	@Test
	public void test_loadCustomer_missingCustomer() throws CustomerLoadException
	{
		// Arrange

		when(m_customerRepository.loadCustomer("999")).thenReturn(null);

		// Act

		try
		{
			m_customerService.loadCustomer("999");

			fail("Expected CustomerLoadException.");
		}
		catch (CustomerLoadException p_exception)
		{
			// Assert

			assertEquals("Customer '999' was not found.", p_exception.getMessage());
		}
	}

	/**
	 * Tests calculating a premium customer discount.
	 */
	@Test
	public void test_calculateDiscount()
	{
		// Arrange

		BigDecimal orderTotal = new BigDecimal("100.00");

		// Act

		BigDecimal discount = m_customerService.calculateDiscount(m_customer, orderTotal);

		// Assert

		assertEquals(new BigDecimal("10.0000"), discount);
	}
}
