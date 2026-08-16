package com.example.customer;

/**
 * Thrown when a customer cannot be loaded.
 */
public class CustomerLoadException extends Exception
{
	/**
	 * Creates a customer load exception.
	 *
	 * @param p_message The exception message.
	 * @param p_cause The original cause.
	 */
	public CustomerLoadException(final String p_message, final Throwable p_cause)
	{
		super(p_message, p_cause);
	}
}
