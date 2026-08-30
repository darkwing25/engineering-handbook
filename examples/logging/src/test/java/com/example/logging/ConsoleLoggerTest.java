package com.example.logging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

/**
 * Tests every application-visible console-logger method.
 */
class ConsoleLoggerTest
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
	 * Tests the constructor happy path with a valid owning class.
	 */
	@Test
	void test_constructor()
	{
		// Arrange

		Class<?> loggerClass = ConsoleLoggerTest.class;

		// Act

		ConsoleLogger logger = new ConsoleLogger(loggerClass);

		// Assert

		assertEquals(loggerClass.getName(), logger.getLoggerName());
	}

	/**
	 * Tests the constructor failure path triggered by a missing owning class.
	 */
	@Test
	void test_constructor_missingClass()
	{
		// Arrange

		Class<?> loggerClass = null;
		Executable constructorOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				new ConsoleLogger(loggerClass);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, constructorOperation);

		// Assert

		assertEquals("The logger class is required.", exception.getMessage());
	}

	/**
	 * Tests the logger-name setter happy path with a replacement name.
	 */
	@Test
	void test_setLoggerName()
	{
		// Arrange

		ConsoleLogger logger = new ConsoleLogger(ConsoleLoggerTest.class);

		// Act

		logger.setLoggerName("ExampleLogger");

		// Assert

		assertEquals("ExampleLogger", logger.getLoggerName());
	}

	/**
	 * Tests the logger-name setter failure path triggered by a missing name.
	 */
	@Test
	void test_setLoggerName_missingName()
	{
		// Arrange

		ConsoleLogger logger = new ConsoleLogger(ConsoleLoggerTest.class);
		Executable setOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				logger.setLoggerName(null);
			}
		};

		// Act

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, setOperation);

		// Assert

		assertEquals("The logger name is required.", exception.getMessage());
	}

	/**
	 * Tests the debug-log happy path with one placeholder argument.
	 */
	@Test
	void test_debug()
	{
		// Arrange

		ConsoleLogger logger = new ConsoleLogger(ConsoleLoggerTest.class);
		Runnable logOperation = new Runnable()
		{
			@Override
			public void run()
			{
				logger.debug("Customer {}.", "123");
			}
		};

		// Act

		String output = captureLog(logOperation);

		// Assert

		assertEquals("DEBUG " + ConsoleLoggerTest.class.getName() + " - Customer 123." + System.lineSeparator(), output);
	}

	/**
	 * Tests the informational-log happy path with two placeholder arguments.
	 */
	@Test
	void test_info()
	{
		// Arrange

		ConsoleLogger logger = new ConsoleLogger(ConsoleLoggerTest.class);
		Runnable logOperation = new Runnable()
		{
			@Override
			public void run()
			{
				logger.info("Customer {} is {}.", "123", "active");
			}
		};

		// Act

		String output = captureLog(logOperation);

		// Assert

		assertEquals("INFO " + ConsoleLoggerTest.class.getName() + " - Customer 123 is active." + System.lineSeparator(), output);
	}

	/**
	 * Tests the warning-log happy path and verifies literal replacement text containing a dollar sign.
	 */
	@Test
	void test_warn()
	{
		// Arrange

		ConsoleLogger logger = new ConsoleLogger(ConsoleLoggerTest.class);
		Runnable logOperation = new Runnable()
		{
			@Override
			public void run()
			{
				logger.warn("Value {}.", "$1");
			}
		};

		// Act

		String output = captureLog(logOperation);

		// Assert

		assertEquals("WARN " + ConsoleLoggerTest.class.getName() + " - Value $1." + System.lineSeparator(), output);
	}

	/**
	 * Tests the error-log happy path without placeholder arguments.
	 */
	@Test
	void test_error()
	{
		// Arrange

		ConsoleLogger logger = new ConsoleLogger(ConsoleLoggerTest.class);
		Runnable logOperation = new Runnable()
		{
			@Override
			public void run()
			{
				logger.error("Operation failed.");
			}
		};

		// Act

		String output = captureLog(logOperation);

		// Assert

		assertEquals("ERROR " + ConsoleLoggerTest.class.getName() + " - Operation failed." + System.lineSeparator(), output);
	}

	// ########################################################################
	// Protected Methods
	// ########################################################################

	// ########################################################################
	// Private Methods
	// ########################################################################

	/**
	 * Captures one log operation while restoring the process output stream.
	 *
	 * @param p_logOperation The log operation.
	 * @return The captured output.
	 */
	private String captureLog(final Runnable p_logOperation)
	{
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintStream originalOutput = System.out;
		String capturedOutput = null;

		try (PrintStream temporaryOutput = new PrintStream(output, true, StandardCharsets.UTF_8))
		{
			System.setOut(temporaryOutput);
			p_logOperation.run();
		}
		finally
		{
			System.setOut(originalOutput);
		}

		capturedOutput = output.toString(StandardCharsets.UTF_8);

		return capturedOutput;
	}

	// ########################################################################
	// Main
	// ########################################################################
}
