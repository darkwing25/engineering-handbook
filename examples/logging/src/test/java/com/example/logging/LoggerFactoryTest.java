package com.example.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests the public logger-factory contract.
 */
class LoggerFactoryTest
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
	 * Tests the logger-factory happy path with a valid owning class.
	 */
	@Test
	void test_getLogger()
	{
		// Arrange

		Class<?> loggerClass = LoggerFactoryTest.class;

		// Act

		ILogger logger = LoggerFactory.getLogger(loggerClass);

		// Assert

		ConsoleLogger consoleLogger = assertInstanceOf(ConsoleLogger.class, logger);
		assertEquals(loggerClass.getName(), consoleLogger.getLoggerName());
	}

	/**
	 * Tests the logger-factory failure path triggered by a missing owning class.
	 */
	@Test
	void test_getLogger_missingClass()
	{
		// Arrange

		Class<?> loggerClass = null;
		Executable getLoggerOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				LoggerFactory.getLogger(loggerClass);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, getLoggerOperation);

		// Assert

		assertEquals("The logger class is required.", exception.getMessage());
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
