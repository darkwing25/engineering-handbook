package com.example.logging;

/**
 * Simple console logger used by the examples.
 */
class ConsoleLogger implements ILogger
{
	// ########################################################################
	// Constants
	// ########################################################################

	// ########################################################################
	// Attributes
	// ########################################################################

	private String m_loggerName;

	// ########################################################################
	// Accessors
	// ########################################################################

	/**
	 * Returns the logger name.
	 *
	 * @return The logger name.
	 */
	public String getLoggerName()
	{
		String loggerName = m_loggerName;

		if (loggerName == null)
		{
			throw new IllegalStateException("The logger name has not been configured.");
		}

		return loggerName;
	}

	/**
	 * Sets the logger name.
	 *
	 * @param p_loggerName The logger name.
	 */
	void setLoggerName(final String p_loggerName)
	{
		if (p_loggerName == null)
		{
			throw new IllegalArgumentException("The logger name is required.");
		}

		m_loggerName = p_loggerName;
	}

	// ########################################################################
	// Constructors
	// ########################################################################

	/**
	 * Creates a console logger.
	 *
	 * @param p_class The class that owns the logger.
	 */
	public ConsoleLogger(final Class<?> p_class)
	{
		setLoggerName(p_class.getName());
	}

	// ########################################################################
	// Public Methods
	// ########################################################################

	@Override
	public void debug(final String p_message, final Object... p_arguments)
	{
		log("DEBUG", p_message, p_arguments);
	}

	@Override
	public void info(final String p_message, final Object... p_arguments)
	{
		log("INFO", p_message, p_arguments);
	}

	@Override
	public void warn(final String p_message, final Object... p_arguments)
	{
		log("WARN", p_message, p_arguments);
	}

	@Override
	public void error(final String p_message, final Object... p_arguments)
	{
		log("ERROR", p_message, p_arguments);
	}

	// ########################################################################
	// Protected Methods
	// ########################################################################

	// ########################################################################
	// Private Methods
	// ########################################################################

	/**
	 * Logs a formatted message.
	 *
	 * @param p_level The log level.
	 * @param p_message The message.
	 * @param p_arguments The message arguments.
	 */
	private void log(final String p_level, final String p_message, final Object... p_arguments)
	{
		String formattedMessage = p_message;

		for (Object argument : p_arguments)
		{
			formattedMessage = formattedMessage.replaceFirst("\\{}", String.valueOf(argument));
		}

		System.out.println(p_level + " " + getLoggerName() + " - " + formattedMessage);
	}

	// ########################################################################
	// Main
	// ########################################################################
}
