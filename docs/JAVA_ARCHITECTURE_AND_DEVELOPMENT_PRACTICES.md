# Java Architecture and Development Practices

This document explains how the language-independent [Software Design Philosophy](SOFTWARE_DESIGN_PHILOSOPHY.md) is applied to Java systems. It teaches object-oriented design as well as recording conventions, because a developer cannot follow a Java standard reliably without understanding the model behind it.

## Object-Oriented Foundations

An object combines a defined responsibility, state, and behaviour behind a deliberate interface. A class describes that object; instantiation creates an instance. Collaborating objects divide a system into parts that can be understood and tested independently.

Encapsulation does not mean placing unrelated operations in one large class. It means an object protects coherent state and exposes behaviour that belongs to its responsibility. A Java class used as a bag of global constants or a long collection of unrelated procedures is not object-oriented merely because the source is inside a class declaration.

Inheritance expresses a genuine substitutable relationship. A subclass must honour the promises of its parent. Prefer collaboration when one object merely needs another object's behaviour; do not use inheritance solely to reuse implementation.

Object-oriented decomposition should make these questions easy to answer:

- What does this object know?
- What does this object do?
- Which invariants does it protect?
- Which collaborators does it require?
- Which failures can it report?
- How can its behaviour be tested in isolation?

Large classes, large methods, deeply nested conditionals or loops, and long parameter lists are design-review signals. They are not automatic numeric violations, but they often reveal mixed responsibilities or missing objects. Apply engineering judgment rather than gaming a measurement.

## Package Organization

Organize packages by feature or business domain:

```text
com.example.customer
com.example.order
```

Prefer this over global layer buckets such as `services`, `repositories`, and `models`.

- Use lowercase package names.
- Keep package hierarchies shallow and meaningful.
- Keep related interfaces, implementations, exceptions, models, and tests close together.
- Treat a package approaching roughly 10-12 types as a responsibility-review signal, not a mechanical limit.
- Mirror the production package hierarchy under tests.

## Collaborators, Construction, and Injection

A business method must not conceal a side-effecting collaborator by constructing it internally. Database clients, repositories, remote-service clients, clocks, file writers, strategy resolvers, and similar collaborators must be replaceable so business behaviour can be tested without performing the side effect.

Creating ordinary values, collections, calculations, and result objects inside a method is normal. The distinction is ownership and observable effects, not the `new` keyword itself.

Use these forms deliberately:

- Constructor injection for a required collaborator that has no safe default.
- Method-parameter injection when the caller legitimately chooses the collaborator for that operation.
- A package-private or protected replacement setter when the class owns a safe default that may be initialized lazily and replaced in a test.
- An injectable factory or resolver when the service owns orchestration but the selected collaborator varies per call.

Do not use field injection or hidden service locators. Validate required dependencies during construction and retain defensive accessor validation where useful. Do not inject loggers; loggers are class-level facade constants.

A setter invoked by a constructor must be non-overridable, normally package-private and `final`. This prevents a constructor from dispatching to incomplete subclass state while retaining a controlled test seam.

### Factory and Strategy Boundary

When a public service method selects a strategy, the service holds an injectable factory or resolver and resolves a strategy on each public call. It then delegates the isolated calculation to a package-private helper that receives the selected strategy directly.

Tests cover both paths:

1. Test the public method with a mocked factory to verify orchestration and delegation.
2. Test the package-private helper with a supplied strategy to isolate its business logic.

The helper test supplements the public-method test; it never replaces it. Static factories are appropriate only for pure, deterministic construction that does not need substitution. Do not use reflection as a normal test seam. JUnit Platform `ReflectionSupport.invokeMethod` is reserved for legacy private code that cannot yet be refactored safely.

## SOLID and Patterns

Use SOLID as design guidance, not as a reason to multiply types:

- Single Responsibility: a class has one coherent reason to change.
- Open/Closed: introduce extension points for real variation.
- Liskov Substitution: subtypes honour parent contracts.
- Interface Segregation: interfaces remain focused without needless fragmentation.
- Dependency Inversion: business logic depends on useful abstractions rather than replaceable infrastructure.

Patterns are tools:

- Use Factory for non-trivial or variable construction.
- Use Builder for complex construction with optional attributes; require mandatory values at builder construction.
- Use Strategy when an algorithm varies by type, policy, or context.
- Use Adapter to translate an external system without owning business decisions.
- Use Facade to simplify a subsystem and isolate replaceable libraries.
- Use Observer only within a bounded area where it does not hide control flow.
- Use Command when an operation needs queuing, retry, scheduling, audit, asynchronous execution, or undo.
- Use Template Method when the workflow is stable and selected steps vary.
- Use Decorator for per-instance behavioural composition; use a facade for application-wide infrastructure concerns.
- Avoid Singleton unless uniqueness is an inherent domain constraint.

## Concurrency and Thread Safety

Classes are not thread-safe by default. Thread safety is an explicit design decision.

Prefer sequential code, immutable data across thread boundaries, explicit mutable-state ownership, standard JDK concurrency utilities, and small synchronized regions. Avoid hidden thread interaction, hand-written locking schemes, and locks held during I/O or external calls.

Every intentionally thread-safe class states that contract in class Javadoc and documents material ownership assumptions.

## Resource and Transaction Ownership

- Prefer `try-with-resources` for `AutoCloseable` resources.
- Keep resource scopes as small as practical.
- The creator or borrower owns cleanup unless ownership is explicitly transferred.
- Do not close caller-owned resources.
- Let `try-with-resources` close an owned buffered wrapper; closing the wrapper flushes its buffered output. Call `flush()` explicitly only when the data must become visible before the resource scope ends.
- A pooled JDBC connection's `close()` normally returns it to the pool.

Transactions belong at the layer that understands the complete business operation, normally the service layer. Repositories participating in a service transaction do not independently commit, roll back, or close the shared connection. Commit and rollback are explicit.

## Exceptions and Logging Boundaries

Use checked exceptions for anticipated, recoverable business and persistence failures. Use unchecked exceptions for programming errors, API misuse, and broken invariants. Preserve the original cause when translating a failure. Retain a concise comment when it explains why an exception is intentionally translated at an application boundary rather than merely repeating the code.

Log a failure once at the boundary that handles it, reports it, or converts it into an operational outcome. Lower layers normally preserve and propagate the cause without logging it. A lower layer may log unique operational context that cannot be reconstructed above, but it must avoid duplicating the same stack trace at each level.

## Performance

Prioritize correctness, readability, credible risk, and measured optimization in that order. Pay particular attention to I/O, database access, repeated allocation in measured hot paths, large retained objects, serialization, and repeated transformations.

Push set operations into a database when it is the appropriate owner. Add caching only when the access pattern and invalidation model justify it. Do not compromise the one-return or clarity rules based on an unsupported just-in-time compiler claim.

## Refactoring

Use this sequence where practical:

1. Add characterization or regression tests.
2. Refactor while preserving behaviour.
3. Implement the requested change.
4. Run the relevant tests after each stage.

Required refactoring is necessary to implement or test safely. Adjacent refactoring is a small, tested improvement directly touching the change. Strategic refactoring is a broad redesign requiring explicit approval and separate planning. Keep scope visible and do not modernize code solely to use newer syntax or frameworks.

## Test-Driven Development and Testability

Tests are the first consumer of the design. Use a red, green, refactor cycle where practical, then cover edge conditions and failure paths.

- Give each test one behavioural purpose; multiple assertions may support that purpose.
- Give every test method Javadoc that identifies the primary or alternate happy path, edge condition, or failure trigger it verifies.
- Use explicit Arrange, Act, Assert sections.
- Use package-private test classes, test methods, and lifecycle methods by default.
- Keep test attributes private unless a test extension specifically requires broader visibility.
- Use the latest stable JUnit release compatible with the project's Java version.
- Use `assertThrows` when it expresses the expected failure clearly.
- Provide callback and `assertThrows` operations through explicit functional-interface implementations when a lambda would require the reader to mentally expand hidden behaviour.
- Create Mockito mocks explicitly in new tests.
- Select the simplest suitable dummy, stub, fake, mock, or spy.
- Mock external systems, slow dependencies, clocks, ID generators, web services, repositories, and databases at unit boundaries.
- Do not mock logging unless logging behaviour is the subject.
- Use real, disposable infrastructure for integration tests where practical.
- Create temporary files dynamically and clean them up.
- Treat flaky tests as defects.

Every application-visible method has a direct behavioural unit test, including constructors, getters, setters, and mechanically generated APIs. Generated code is not exempt because generation does not prove that the specification, mapping, key, or generator configuration is correct. Compiler-created synthetic internals outside the observable contract are not direct test targets.

Greenfield CI enforces at least 80% line and branch coverage. Brownfield projects record a baseline, prevent regression, and improve deliberately. Exclusions are documented and approved. Coverage never substitutes for testing meaningful behaviour.

Quality gates apply at pull-request and merge boundaries. A coherent, independently buildable commit is encouraged but not required.
