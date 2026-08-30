package com.example.testing;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Writes customer data to a CSV file.
 */
public class CustomerFileWriter
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

	/**
	 * Creates a customer file writer.
	 */
	public CustomerFileWriter()
	{
	}

	// ########################################################################
	// Public Methods
	// ########################################################################

	/**
	 * Writes a customer CSV file.
	 *
	 * @param p_file The output file.
	 * @param p_customerId The customer identifier.
	 * @param p_customerName The customer name.
	 * @throws CustomerFileWriteException Thrown when the file cannot be written.
	 */
	public void writeCustomer(final Path p_file, final String p_customerId, final String p_customerName) throws CustomerFileWriteException
	{
		try (BufferedWriter writer = Files.newBufferedWriter(p_file))
		{
			writer.write("customerId,name");
			writer.newLine();
			writer.write(p_customerId + "," + p_customerName);
			writer.newLine();
		}
		catch (IOException p_exception)
		{
			// Convert the file-system exception into an application-specific checked exception.
			throw new CustomerFileWriteException("Unable to write the customer file.", p_exception);
		}
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
