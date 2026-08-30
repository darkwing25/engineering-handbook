# Customer Example

This example demonstrates:

- feature-oriented packages,
- constructor injection,
- a safe lazy default with a package-private replacement seam,
- an injectable strategy factory resolved on each public call,
- direct tests of both public orchestration and package-private business logic,
- fail-fast dependency validation,
- a class-level logging facade,
- checked exceptions,
- factories,
- Strategy for customer discounts,
- explicit one-return methods,
- direct behavioural tests for constructors and accessors,
- Javadoc identifying every test's exact behavioural path,
- explicit functional-interface implementations instead of lambdas,
- and package-private Arrange/Act/Assert tests.

It is not a complete production application. It is a compact example package showing the standards in code.

From the repository root, build and test this example and its logging dependency with Java 25 or later:

```shell
mvn -pl :customer-example -am clean verify
```

The build compiles with Java release 25, runs JUnit 6 tests, and enforces at least 80% line and branch coverage.
