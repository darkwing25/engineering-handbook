package com.example.logging;

/**
 * Defines the logging facade used by application code.
 */
public interface ILogger
{
	/**
	 * Logs a debug message.
	 *
	 * @param p_message The message.
	 * @param p_arguments The message arguments.
	 */
	void debug(final String p_message, final Object... p_arguments);

	/**
	 * Logs an informational message.
	 *
	 * @param p_message The message.
	 * @param p_arguments The message arguments.
	 */
	void info(final String p_message, final Object... p_arguments);

	/**
	 * Logs a warning message.
	 *
	 * @param p_message The message.
	 * @param p_arguments The message arguments.
	 */
	void warn(final String p_message, final Object... p_arguments);

	/**
	 * Logs an error message.
	 *
	 * @param p_message The message.
	 * @param p_arguments The message arguments.
	 */
	void error(final String p_message, final Object... p_arguments);
}
