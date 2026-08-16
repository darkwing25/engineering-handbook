# Java Architecture and Development Practices

This document describes how Java systems should be designed, evolved, and tested.

## Package Organization

Organize packages by feature or business domain:

```text
com.example.customer
com.example.order
```

Prefer this over global layer buckets such as `services`, `repositories`, and `models`.

Rules:

- Use lowercase package names.
- Keep package hierarchies shallow and meaningful.
- Keep related interfaces, implementations, exceptions, models, and tests close together.
- Aim for no more than roughly 10-12 classes per package.
- Treat larger packages as a design review trigger, not an automatic violation.
- Mirror the production package hierarchy under tests.

## Dependency Injection and Construction

- Prefer explicit constructor injection.
- Avoid field injection.
- Avoid hidden framework wiring.
- Use setters only for optional or controlled replacement.
- Keep dependency setters package-private or protected unless they are part of the public API.
- Validate required dependencies during construction.
- Retain defensive getter validation where useful.
- Do not pass loggers through constructors; loggers are class constants.

## SOLID

Use SOLID as design guidance, not rigid doctrine.

- Single Responsibility: a class should have one clear reason to change.
- Open/Closed: create extension points when real variation exists, not for hypothetical flexibility.
- Liskov Substitution: subclasses must honour parent contracts.
- Interface Segregation: keep interfaces focused without fragmenting needlessly.
- Dependency Inversion: business logic should depend on useful abstractions, not infrastructure implementations.

Class size, package size, and parameter count are warning signals. They should prompt a responsibility review.

## Design Patterns

Patterns are engineering tools. Use them to solve engineering problems, not to demonstrate knowledge of patterns.

### Factory

Use factories when construction is non-trivial, multiple implementations exist, or testability improves.

### Builder

Use builders for complex objects with optional attributes. Required attributes belong in the builder constructor.

### Strategy

Use Strategy when behaviour varies by type, policy, or context. Prefer a strategy and factory over a large `switch` that selects algorithms.

### Adapter

Adapters translate; they do not decide. Use adapters for external APIs, vendor libraries, legacy systems, protocols, data conversion, and vendor exception mapping.

### Facade

Use facades to simplify subsystems and isolate application code from implementation details. Facades may orchestrate related subsystem operations, but business decisions belong in services or strategies.

### Observer

Use Observer within a bounded subsystem when decoupled event handling improves design. Avoid widespread observer use when it hides control flow.

### Command

Use Command when an operation has a lifecycle beyond a direct method call: queuing, retrying, scheduling, auditing, asynchronous execution, distributed execution, undo, or per-item failure handling.

### Template Method

Use Template Method when the workflow is fixed and only selected steps vary. Prefer Strategy when the sequence itself changes.

### Decorator

Decorator is acceptable when behaviour varies per object instance at runtime. For project-wide cross-cutting concerns such as logging, timing, auditing, or metrics, prefer centralizing behaviour in a facade.

### Singleton

Avoid Singleton by default. Use it only when uniqueness is inherent to the object.

## Concurrency and Thread Safety

Classes are not thread-safe by default. Thread safety is an explicit design decision.

Prefer:

- simple sequential code,
- immutable data crossing thread boundaries,
- explicit ownership of mutable state,
- standard JDK concurrency utilities,
- and small synchronized regions.

Avoid:

- shared mutable state,
- unnecessary synchronization,
- hand-written locking schemes,
- hidden thread interactions,
- locks held during I/O or external calls.

Every intentionally thread-safe class must say so in class Javadoc. Important threading assumptions must also be documented.

## Resource Management

- Prefer `try-with-resources` for `AutoCloseable` resources.
- Keep resource scopes as small as practical.
- The creator or borrower owns cleanup unless ownership is explicitly transferred.
- Do not close caller-owned resources.
- For pooled JDBC connections, `close()` normally returns the connection to the pool.
- Cleanup should occur immediately in the smallest reasonable scope.

## Transactions

Transaction boundaries should normally live at the layer that understands the complete business operation, usually the service layer.

Repositories participating in a service transaction should not independently commit, roll back, or close the shared connection.

Commit and rollback must be explicit.

## Performance

Design with performance awareness, but optimize with evidence.

Prioritize:

1. Correctness.
2. Readability and maintainability.
3. Credible performance risks.
4. Measured optimization.

Pay attention to predictable hotspots:

- I/O,
- database access,
- repeated object allocation,
- large in-memory objects,
- serialization,
- and repeated transformations.

Prefer pushing filtering, sorting, aggregation, and set operations into the database where practical. Use caching proactively when the access pattern clearly justifies it, but do not add caching because it is fashionable.

## Refactoring

Preferred sequence:

1. Add characterization or regression tests first.
2. Refactor while preserving behaviour.
3. Implement the requested change.
4. Run the relevant test suite after each stage.

Refactoring categories:

- Required refactoring: necessary to test or safely implement the requested behaviour.
- Adjacent refactoring: small, tested improvements directly touching changed code.
- Strategic refactoring: broad redesign requiring explicit approval, estimation, and separate tracking.

Do not modernize code merely to use newer syntax, libraries, or frameworks.

## Test-Driven Development and Testability

Tests are the first consumer of a design.

TDD cycle:

1. Write a happy-path test for the behaviour.
2. Confirm it fails for the expected reason when practical.
3. Write code to satisfy the test.
4. Refactor the test and production code.
5. Add edge and unhappy-path tests.

Testing standards:

- One behaviour per test.
- Multiple assertions are acceptable when they verify one behaviour.
- Use Arrange, Act, Assert comments.
- Use the latest stable JUnit compatible with the supported Java version.
- Use explicit Mockito mock creation for new tests.
- Understand dummies, stubs, mocks, fakes, and spies, but do not get hung up on terminology.
- Choose the simplest test double that clearly expresses intent.
- Use explicit try/catch for exception assertions.
- Mock external systems, slow dependencies, clocks, ID generators, web services, repositories, and databases.
- Do not mock logging unless logging is the feature.
- Integration tests should use real infrastructure when practical.
- Test databases must be isolated and disposable.
- Temporary files must be created dynamically and cleaned up.
- Flaky tests are defects.
- Use containers and CI to ensure tests behave across operating systems.
- Mirror production package structure in tests.
- Every commit should compile, run unit tests, run static analysis, pass linters and formatting checks, run integration tests where applicable, and meet the agreed coverage goal.

Coverage is a guide. Aim high, accept practical thresholds such as 80% where appropriate, and investigate unreachable code.
