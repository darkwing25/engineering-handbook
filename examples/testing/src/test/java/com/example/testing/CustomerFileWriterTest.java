package com.example.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.io.TempDir;

/**
 * Tests every application-visible customer file-writer method.
 */
class CustomerFileWriterTest
{
	// ########################################################################
	// Constants
	// ########################################################################

	// ########################################################################
	// Attributes
	// ########################################################################

	@TempDir
	private Path m_temporaryDirectory;

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
	 * Tests the primary constructor happy path by creating a usable writer.
	 */
	@Test
	void test_constructor()
	{
		// Arrange

		CustomerFileWriter writer = null;

		// Act

		writer = new CustomerFileWriter();

		// Assert

		assertInstanceOf(CustomerFileWriter.class, writer);
	}

	/**
	 * Tests the primary write happy path by writing and reading a complete customer CSV file.
	 *
	 * @throws CustomerFileWriteException Thrown when the customer file cannot be written.
	 * @throws IOException Thrown when the written customer file cannot be read.
	 */
	@Test
	void test_writeCustomer() throws CustomerFileWriteException, IOException
	{
		// Arrange

		CustomerFileWriter writer = new CustomerFileWriter();
		Path outputFile = m_temporaryDirectory.resolve("customers.csv");

		// Act

		writer.writeCustomer(outputFile, "123", "Ada Lovelace");
		List<String> lines = Files.readAllLines(outputFile);

		// Assert

		assertTrue(Files.exists(outputFile));
		assertEquals("customerId,name", lines.get(0));
		assertEquals("123,Ada Lovelace", lines.get(1));
	}

	/**
	 * Tests the file-failure path triggered when the requested output path is a directory.
	 *
	 * @throws IOException Thrown when the test cannot prepare its temporary directory.
	 */
	@Test
	void test_writeCustomer_fileFailure() throws IOException
	{
		// Arrange

		CustomerFileWriter writer = new CustomerFileWriter();
		Path directory = Files.createDirectory(m_temporaryDirectory.resolve("not-a-file"));
		Executable writeOperation = new Executable()
		{
			@Override
			public void execute() throws Throwable
			{
				writer.writeCustomer(directory, "123", "Ada Lovelace");
			}
		};

		// Act

		CustomerFileWriteException exception = assertThrows(CustomerFileWriteException.class, writeOperation);

		// Assert

		assertEquals("Unable to write the customer file.", exception.getMessage());
		assertInstanceOf(IOException.class, exception.getCause());
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
