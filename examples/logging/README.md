# Logging Example

This example demonstrates an application-owned logging facade.

Application classes depend on `ILogger` and `LoggerFactory`, not on a specific backend such as Log4j, SLF4J, Logback, or `java.util.logging`.

The included `ConsoleLogger` is deliberately simple. In a real project, the facade would delegate to the selected logging backend.
