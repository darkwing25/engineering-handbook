package com.example.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests the customer file-write exception contract.
 */
class CustomerFileWriteExceptionTest
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
	 * Tests the constructor happy path by preserving the supplied message and cause.
	 */
	@Test
	void test_constructor()
	{
		// Arrange

		IOException cause = new IOException("Disk unavailable.");

		// Act

		CustomerFileWriteException exception = new CustomerFileWriteException("Unable to write.", cause);

		// Assert

		assertEquals("Unable to write.", exception.getMessage());
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
