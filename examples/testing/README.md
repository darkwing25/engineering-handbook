# Testing Examples

This directory demonstrates testing rules from the handbook:

- Arrange, Act, Assert comments.
- One behaviour per test.
- Javadoc identifying each test's happy path, edge condition, or failure trigger.
- Explicit `Executable` implementations for `assertThrows` operations instead of lambdas.
- Temporary directories instead of developer-specific file paths.
- Real filesystem verification when the behaviour is file output.
- Checked exception wrapping for file failures.
- Package-private JUnit test classes and methods, with private test attributes unless an extension requires broader visibility.
- Direct tests of constructors and exception contracts.

The example intentionally writes a real temporary file because mocking the file write would not verify the behaviour that matters.

From the repository root, build and test only this example with Java 25 or later:

```shell
mvn -pl :testing-example clean verify
```

The build enforces at least 80% line and branch coverage.
