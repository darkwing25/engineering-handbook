package com.example.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Test;

/**
 * Tests customer file writing.
 */
public class CustomerFileWriterTest
{
	@TempDir
	private Path m_temporaryDirectory;

	/**
	 * Tests writing a customer CSV file.
	 *
	 * @throws Exception Thrown when the test cannot read the generated file.
	 */
	@Test
	public void test_writeCustomer() throws Exception
	{
		// Arrange

		CustomerFileWriter writer = new CustomerFileWriter();
		Path outputFile = m_temporaryDirectory.resolve("customers.csv");

		// Act

		writer.writeCustomer(outputFile, "123", "Ada Lovelace");

		// Assert

		List<String> lines = Files.readAllLines(outputFile);

		assertTrue(Files.exists(outputFile));
		assertEquals("customerId,name", lines.get(0));
		assertEquals("123,Ada Lovelace", lines.get(1));
	}
}
