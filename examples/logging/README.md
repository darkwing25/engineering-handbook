# Logging Example

This example demonstrates an application-owned logging facade.

Application classes depend on `ILogger` and `LoggerFactory`, not on a specific backend such as Log4j, SLF4J, Logback, or `java.util.logging`.

The included `ConsoleLogger` is deliberately simple. In a real project, the facade would delegate to the selected logging backend.

From the repository root, build and test only this example with Java 25 or later:

```shell
mvn -pl :logging-example clean verify
```

The tests directly exercise construction, accessors, every facade level, literal placeholder replacement, and the factory. Test Javadoc identifies each behavioural path, and callback operations are expanded into explicit `Runnable` or `Executable` implementations instead of lambdas. The build enforces at least 80% line and branch coverage.
