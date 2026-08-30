# Java Coding Standard

This standard defines how Java code should look and read. These conventions reduce visual and structural surprise so a developer can concentrate on behaviour and architecture.

## Formatting

- Use tabs for indentation and configure the IDE to display a tab as 3 spaces.
- Use Allman braces.
- Always use braces for `if`, `else`, `for`, `while`, `do`, `switch`, and similar structures.
- Do not put blank lines immediately inside braces.
- Use blank lines to separate logical blocks.
- Keep a declaration or expression on one line when it fits within the preferred line length. Do not introduce a line break that separates a short `throws` clause or otherwise makes a simple thought harder to scan.
- Treat lines near 120 characters, deeply nested control flow, large methods, and large classes as design-review signals rather than mechanical limits.

## Naming

| Element | Standard |
| --- | --- |
| Class, interface, enumeration, record, annotation | `PascalCase` |
| Method | `camelCase` |
| Attribute | `m_camelCase` |
| Parameter | `p_camelCase` |
| Local variable | `camelCase` |
| Constant | `UPPER_SNAKE_CASE` |

Do not use an `l_` prefix for local variables.

## Constants and Attributes

Constants are `static final` and use constant naming. An instance-level `final` attribute is an immutable attribute, not a constant, and uses attribute naming.

Attributes are normally private. Accessors preserve predictable initialization, validation, and controlled test seams. Avoid global bags of constants; keep a constant with the responsibility that gives it meaning.

## Type Layout

Every Java type uses the full set of section banners in this order, including classes, interfaces, enumerations, records, annotations, and test types. Keep empty sections until a later EDR establishes a specialized layout for a type category.

```java
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

// ########################################################################
// Public Methods
// ########################################################################

// ########################################################################
// Protected Methods
// ########################################################################

// ########################################################################
// Private Methods
// ########################################################################

// ########################################################################
// Main
// ########################################################################
```

The banners are mandatory even though comments are removed from compiled bytecode. They make the source predictable and give future additions an unambiguous home.

## Parameters

- Declare constructor and method parameters `final`.
- Prefix parameters with `p_`.
- Keep a parameter list on one line.
- If it exceeds the preferred line length, review whether a parameter object or a responsibility split better expresses the design.

## Javadoc

Public types and public methods require Javadoc. Document purpose, parameters, non-void return values, checked exceptions, and important threading or ownership assumptions. Keep tag descriptions on one line when practical.

Package-private test seams require a comment or Javadoc when their testing purpose and production role are not self-evident.

Every unit-test method requires Javadoc that identifies the specific behaviour it verifies. State whether the test covers a primary happy path, an alternate happy path, an edge condition, or a failure path, and name the triggering condition when the method name does not already make it unambiguous.

## Control Flow

- Every method has exactly one return statement, placed at the end of the method.
- A `void` method completes through its single natural exit and does not use explicit early `return` statements.
- Do not use early returns, including guard-clause returns.
- Return an expression directly when a method does nothing except produce that expression. Do not introduce a local variable solely to return it on the next line.
- Use a named result variable when branches, validation, mutation, cleanup, or a meaningful intermediate name helps explain how the result is produced.
- Use direct null checks such as `p_customer != null`.
- Use `Objects.nonNull` mainly where a method reference is required.
- Prefer ordinary loops and conditionals over streams and lambdas.
- Do not use a lambda when an explicit functional-interface implementation makes the executed behaviour easier to see. Handbook examples expand functional interfaces explicitly; a project exception requires documented rationale.

The one-return rule is a readability, review, and debugging convention. Do not justify it with speculative JIT performance claims.

## Constructors and Accessors

Constructors initialize state through setters when doing so keeps validation consistent. A setter called during construction must be non-overridable, normally package-private and `final`. Direct assignment is appropriate when calling a setter would be unsafe or misleading.

Required dependencies are constructor-injected and validated. A lazily initialized collaborator is allowed only when the class owns a safe default. Its replacement setter remains narrowly visible.

Write behavioural tests for constructors and every accessor. A getter and setter are simple only when their implementation and mapping are correct; simplicity is not evidence.

## Exceptions

- Use checked exceptions for anticipated recoverable business and persistence errors.
- Use unchecked exceptions for programming errors, API misuse, and broken invariants.
- Throw a specific exception rather than generic `Exception`.
- Preserve the original cause when wrapping.
- Do not silently swallow an exception.
- Explain a catch block when the reason for handling is not self-evident. Retain a concise comment when it explains why an exception is intentionally translated at an application boundary.
- Log the failure once at the boundary that handles or reports it; do not log and rethrow at every layer.

## Logging

Application code logs through an application-owned facade rather than depending directly on a logging implementation. Declare one class-level logger:

```java
private static final ILogger LOGGER = LoggerFactory.getLogger(CustomerService.class);
```

Use levels consistently:

- `DEBUG` for developer diagnostics.
- `INFO` for normal milestones and informational operational events.
- `WARN` for recoverable unexpected conditions or warning notifications.
- `ERROR` for failures that prevent an operation from completing.

`TRACE` is not required by default. Include useful context without secrets or unnecessarily sensitive data.

## Collections and Generics

- Declare collections through `List`, `Map`, or `Set` and instantiate an appropriate implementation.
- Collection accessors do not return `null`.
- Lazily initialize mutable collection attributes when the class owns an empty default.
- Expose immutable or unmodifiable data when callers must not mutate it.
- Make defensive copies when ownership or invariants require them.
- Never use raw generic types.
- Prefer concrete generic signatures and avoid wildcard complexity unless it solves a real API problem.

## Loops and Switches

Prefer an enhanced `for` loop, then an indexed loop when the index matters, then an iterator when modifying during iteration. Use a stream only when it is clearer.

Use classic `switch` syntax by default. Include `break` unless intentional fall-through is documented, and include a `default` branch. A switch expression is acceptable only when it materially improves clarity and still respects the one-return rule.

## Annotations

- Put one annotation per line above its target.
- Alphabetize multiple annotations.
- Always use `@Override` where applicable.
- Pair `@Deprecated` with Javadoc explaining the reason, replacement, and removal plan when known.
- Keep `@SuppressWarnings` narrow and explain why the warning cannot reasonably be fixed.
- Use one nullability family if the project adopts one.
- Avoid field-injection annotations and unjustified custom annotations.

## Testing Style

Unit tests use package-private classes and methods by default because JUnit recommends omitting `public` unless a technical constraint requires it. Test attributes remain private unless a test extension specifically requires broader visibility. Tests use explicit Arrange, Act, Assert sections. Every test method has Javadoc that identifies its exact behavioural path or triggering condition. `assertThrows` is allowed when it states an expected exception more clearly than manual handling; provide its `Executable` explicitly rather than hiding the operation in a lambda. Helper-seam tests supplement direct public-method tests rather than replacing them.

See [Java Architecture and Development Practices](JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md) and the independently buildable projects under `examples/`.
