package com.example.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests public customer-service behaviour and its package-private test seams.
 */
class CustomerServiceTest
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
	 * Tests the constructor happy path with a required repository.
	 */
	@Test
	void test_constructor()
	{
		// Arrange

		CustomerRepository repository = mock(CustomerRepository.class);

		// Act

		CustomerService service = new CustomerService(repository);

		// Assert

		assertSame(repository, service.getCustomerRepository());
	}

	/**
	 * Tests the constructor failure path triggered by a missing repository.
	 */
	@Test
	void test_constructor_missingRepository()
	{
		// Arrange

		CustomerRepository repository = null;
		Executable constructorOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				new CustomerService(repository);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, constructorOperation);

		// Assert

		assertEquals("The customer repository is required.", exception.getMessage());
	}

	/**
	 * Tests the repository replacement-seam happy path.
	 */
	@Test
	void test_setCustomerRepository()
	{
		// Arrange

		CustomerRepository originalRepository = mock(CustomerRepository.class);
		CustomerRepository replacementRepository = mock(CustomerRepository.class);
		CustomerService service = new CustomerService(originalRepository);

		// Act

		service.setCustomerRepository(replacementRepository);

		// Assert

		assertSame(replacementRepository, service.getCustomerRepository());
	}

	/**
	 * Tests the repository replacement-seam failure path triggered by a missing repository.
	 */
	@Test
	void test_setCustomerRepository_missingRepository()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));
		Executable setOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				service.setCustomerRepository(null);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, setOperation);

		// Assert

		assertEquals("The customer repository is required.", exception.getMessage());
	}

	/**
	 * Tests the lazy-factory edge path and verifies that the class-owned default is retained.
	 */
	@Test
	void test_getCustomerDiscountStrategyFactory_default()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));

		// Act

		CustomerDiscountStrategyFactory firstFactory = service.getCustomerDiscountStrategyFactory();
		CustomerDiscountStrategyFactory secondFactory = service.getCustomerDiscountStrategyFactory();

		// Assert

		assertSame(firstFactory, secondFactory);
	}

	/**
	 * Tests the strategy-factory replacement-seam happy path.
	 */
	@Test
	void test_setCustomerDiscountStrategyFactory()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));
		CustomerDiscountStrategyFactory factory = mock(CustomerDiscountStrategyFactory.class);

		// Act

		service.setCustomerDiscountStrategyFactory(factory);

		// Assert

		assertSame(factory, service.getCustomerDiscountStrategyFactory());
	}

	/**
	 * Tests the strategy-factory replacement-seam failure path triggered by a missing factory.
	 */
	@Test
	void test_setCustomerDiscountStrategyFactory_missingFactory()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));
		Executable setOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				service.setCustomerDiscountStrategyFactory(null);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, setOperation);

		// Assert

		assertEquals("The customer discount strategy factory is required.", exception.getMessage());
	}

	/**
	 * Tests the customer-load happy path when the repository finds the requested customer.
	 *
	 * @throws CustomerLoadException Thrown when the repository cannot complete the lookup.
	 */
	@Test
	void test_loadCustomer() throws CustomerLoadException
	{
		// Arrange

		Customer customer = new Customer("123");
		CustomerRepository repository = mock(CustomerRepository.class);
		CustomerService service = new CustomerService(repository);
		when(repository.loadCustomer("123")).thenReturn(customer);

		// Act

		Customer loadedCustomer = service.loadCustomer("123");

		// Assert

		assertSame(customer, loadedCustomer);
		verify(repository).loadCustomer("123");
	}

	/**
	 * Tests the customer-load edge path when the repository returns no matching customer.
	 *
	 * @throws CustomerLoadException Thrown when the repository cannot complete the lookup.
	 */
	@Test
	void test_loadCustomer_missingCustomer() throws CustomerLoadException
	{
		// Arrange

		CustomerRepository repository = mock(CustomerRepository.class);
		CustomerService service = new CustomerService(repository);
		when(repository.loadCustomer("999")).thenReturn(null);
		Executable loadOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				service.loadCustomer("999");
			}
		};

		// Act

		CustomerLoadException exception = assertThrows(CustomerLoadException.class, loadOperation);

		// Assert

		assertEquals("Customer '999' was not found.", exception.getMessage());
		verify(repository).loadCustomer("999");
	}

	/**
	 * Tests the customer-load failure path when the repository propagates a storage failure.
	 *
	 * @throws CustomerLoadException Thrown while configuring the mocked repository failure.
	 */
	@Test
	void test_loadCustomer_repositoryFailure() throws CustomerLoadException
	{
		// Arrange

		CustomerLoadException expectedException = new CustomerLoadException("Storage unavailable.", null);
		CustomerRepository repository = mock(CustomerRepository.class);
		CustomerService service = new CustomerService(repository);
		when(repository.loadCustomer("123")).thenThrow(expectedException);
		Executable loadOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				service.loadCustomer("123");
			}
		};

		// Act

		CustomerLoadException actualException = assertThrows(CustomerLoadException.class, loadOperation);

		// Assert

		assertSame(expectedException, actualException);
	}

	/**
	 * Tests the public discount happy path and verifies that it resolves a strategy for every call.
	 */
	@Test
	void test_calculateDiscount_publicMethodResolvesEachCall()
	{
		// Arrange

		Customer customer = new Customer("123");
		customer.setCustomerType(CustomerType.PREMIUM);
		CustomerRepository repository = mock(CustomerRepository.class);
		CustomerDiscountStrategyFactory factory = mock(CustomerDiscountStrategyFactory.class);
		CustomerDiscountStrategy strategy = mock(CustomerDiscountStrategy.class);
		CustomerService service = new CustomerService(repository);
		BigDecimal total = new BigDecimal("100.00");
		BigDecimal expectedDiscount = new BigDecimal("10.00");
		service.setCustomerDiscountStrategyFactory(factory);
		when(factory.createStrategy(CustomerType.PREMIUM)).thenReturn(strategy);
		when(strategy.calculateDiscount(total)).thenReturn(expectedDiscount);

		// Act

		BigDecimal firstDiscount = service.calculateDiscount(customer, total);
		BigDecimal secondDiscount = service.calculateDiscount(customer, total);

		// Assert

		assertEquals(expectedDiscount, firstDiscount);
		assertEquals(expectedDiscount, secondDiscount);
		verify(factory, times(2)).createStrategy(CustomerType.PREMIUM);
		verify(strategy, times(2)).calculateDiscount(total);
	}

	/**
	 * Tests the public discount edge path triggered by a missing customer.
	 */
	@Test
	void test_calculateDiscount_missingCustomer()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));

		// Act

		BigDecimal discount = service.calculateDiscount(null, new BigDecimal("100.00"));

		// Assert

		assertEquals(BigDecimal.ZERO, discount);
	}

	/**
	 * Tests the package-private discount-helper happy path with a caller-supplied strategy.
	 */
	@Test
	void test_calculateDiscount_helperMethod()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));
		CustomerDiscountStrategy strategy = mock(CustomerDiscountStrategy.class);
		BigDecimal total = new BigDecimal("100.00");
		BigDecimal expectedDiscount = new BigDecimal("8.00");
		when(strategy.calculateDiscount(total)).thenReturn(expectedDiscount);

		// Act

		BigDecimal discount = service.calculateDiscount(total, strategy);

		// Assert

		assertEquals(expectedDiscount, discount);
		verify(strategy).calculateDiscount(total);
	}

	/**
	 * Tests the package-private discount-helper failure path triggered by a missing strategy.
	 */
	@Test
	void test_calculateDiscount_helperMethodMissingStrategy()
	{
		// Arrange

		CustomerService service = new CustomerService(mock(CustomerRepository.class));
		Executable calculateOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				service.calculateDiscount(BigDecimal.TEN, null);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, calculateOperation);

		// Assert

		assertEquals("The customer discount strategy is required.", exception.getMessage());
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
