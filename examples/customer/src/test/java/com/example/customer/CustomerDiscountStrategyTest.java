package com.example.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * Tests discount strategies, their factory, and the generated enumeration API.
 */
class CustomerDiscountStrategyTest
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
	 * Tests the strategy-factory constructor happy path.
	 */
	@Test
	void test_factoryConstructor()
	{
		// Arrange

		CustomerDiscountStrategyFactory factory = null;

		// Act

		factory = new CustomerDiscountStrategyFactory();

		// Assert

		assertInstanceOf(CustomerDiscountStrategyFactory.class, factory);
	}

	/**
	 * Tests the factory branch that selects the premium strategy.
	 */
	@Test
	void test_createStrategy_premium()
	{
		// Arrange

		CustomerDiscountStrategyFactory factory = new CustomerDiscountStrategyFactory();

		// Act

		CustomerDiscountStrategy strategy = factory.createStrategy(CustomerType.PREMIUM);

		// Assert

		assertInstanceOf(PremiumCustomerDiscountStrategy.class, strategy);
	}

	/**
	 * Tests the factory branch that selects the standard strategy.
	 */
	@Test
	void test_createStrategy_standard()
	{
		// Arrange

		CustomerDiscountStrategyFactory factory = new CustomerDiscountStrategyFactory();

		// Act

		CustomerDiscountStrategy strategy = factory.createStrategy(CustomerType.STANDARD);

		// Assert

		assertInstanceOf(StandardCustomerDiscountStrategy.class, strategy);
	}

	/**
	 * Tests the factory edge path that defaults a missing customer type to the standard strategy.
	 */
	@Test
	void test_createStrategy_missingType()
	{
		// Arrange

		CustomerDiscountStrategyFactory factory = new CustomerDiscountStrategyFactory();

		// Act

		CustomerDiscountStrategy strategy = factory.createStrategy(null);

		// Assert

		assertInstanceOf(StandardCustomerDiscountStrategy.class, strategy);
	}

	/**
	 * Tests the premium-strategy constructor happy path.
	 */
	@Test
	void test_premiumStrategyConstructor()
	{
		// Arrange

		PremiumCustomerDiscountStrategy strategy = null;

		// Act

		strategy = new PremiumCustomerDiscountStrategy();

		// Assert

		assertInstanceOf(PremiumCustomerDiscountStrategy.class, strategy);
	}

	/**
	 * Tests the premium-discount happy path with a non-null order total.
	 */
	@Test
	void test_premiumCalculateDiscount()
	{
		// Arrange

		PremiumCustomerDiscountStrategy strategy = new PremiumCustomerDiscountStrategy();

		// Act

		BigDecimal discount = strategy.calculateDiscount(new BigDecimal("100.00"));

		// Assert

		assertEquals(new BigDecimal("10.0000"), discount);
	}

	/**
	 * Tests the premium-discount edge path triggered by a missing order total.
	 */
	@Test
	void test_premiumCalculateDiscount_missingTotal()
	{
		// Arrange

		PremiumCustomerDiscountStrategy strategy = new PremiumCustomerDiscountStrategy();

		// Act

		BigDecimal discount = strategy.calculateDiscount(null);

		// Assert

		assertEquals(BigDecimal.ZERO, discount);
	}

	/**
	 * Tests the standard-strategy constructor happy path.
	 */
	@Test
	void test_standardStrategyConstructor()
	{
		// Arrange

		StandardCustomerDiscountStrategy strategy = null;

		// Act

		strategy = new StandardCustomerDiscountStrategy();

		// Assert

		assertInstanceOf(StandardCustomerDiscountStrategy.class, strategy);
	}

	/**
	 * Tests the standard-discount happy path by applying its zero-percent rate to an order total.
	 */
	@Test
	void test_standardCalculateDiscount()
	{
		// Arrange

		StandardCustomerDiscountStrategy strategy = new StandardCustomerDiscountStrategy();

		// Act

		BigDecimal discount = strategy.calculateDiscount(new BigDecimal("100.00"));

		// Assert

		assertEquals(0, BigDecimal.ZERO.compareTo(discount));
	}

	/**
	 * Tests the standard-discount edge path triggered by a missing order total.
	 */
	@Test
	void test_standardCalculateDiscount_missingTotal()
	{
		// Arrange

		StandardCustomerDiscountStrategy strategy = new StandardCustomerDiscountStrategy();

		// Act

		BigDecimal discount = strategy.calculateDiscount(null);

		// Assert

		assertEquals(BigDecimal.ZERO, discount);
	}

	/**
	 * Tests the generated enumeration-values happy path and expected constant count.
	 */
	@Test
	void test_customerTypeValues()
	{
		// Arrange

		int expectedCount = 2;

		// Act

		CustomerType[] customerTypes = CustomerType.values();

		// Assert

		assertEquals(expectedCount, customerTypes.length);
	}

	/**
	 * Tests the generated enumeration lookup happy path for the premium constant.
	 */
	@Test
	void test_customerTypeValueOf()
	{
		// Arrange

		String typeName = "PREMIUM";

		// Act

		CustomerType customerType = CustomerType.valueOf(typeName);

		// Assert

		assertEquals(CustomerType.PREMIUM, customerType);
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
