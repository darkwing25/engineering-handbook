# Java Coding Standard

This standard defines how Java code should look and read. The purpose is predictable, explicit, maintainable Java.

## Formatting

- Use tabs for indentation.
- Configure the IDE to display tabs as 3 spaces.
- Use Allman brace style.
- Always use braces for `if`, `else`, `for`, `while`, `do`, and similar control structures.
- Do not put blank lines immediately inside braces.
- Use blank lines to separate logical blocks.
- Target approximately 120 characters per line. Treat this as a design review signal, not a rigid mechanical limit.

## Naming

| Element | Standard |
| --- | --- |
| Class | `PascalCase` |
| Interface | `PascalCase` |
| Method | `camelCase` |
| Attribute | `m_camelCase` |
| Parameter | `p_camelCase` |
| Local variable | `camelCase` |
| Constant | `UPPER_SNAKE_CASE` |

Do not use an `l_` prefix for local variables.

## Constants and Attributes

Constants are always `static final`:

```java
private static final int DEFAULT_CUSTOMER_LIMIT = 100;
```

Instance-level `final` attributes are immutable attributes, not constants, and follow normal attribute naming.

Attributes should normally be private. Use accessors to preserve consistent initialization, validation, and test seams.

## Class Layout

Every class uses these sections in this order, even when a section is empty:

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

## Parameters

- Declare method and constructor parameters `final`.
- Use `p_` prefixes.
- Keep parameter lists on one line.
- If a parameter list pushes past the preferred line length, review the design.

Possible fixes include a parameter object, a map or collection when semantically appropriate, or splitting responsibilities.

## Javadoc

Public classes and public methods require Javadoc.

Every public method should document:

- purpose,
- parameters,
- return value when not `void`,
- thrown checked exceptions,
- and important threading or ownership assumptions.

Keep `@param`, `@return`, and `@throws` descriptions on the same line when practical.

## Control Flow

- Prefer one return statement per method.
- Avoid early returns.
- Use direct null checks such as `p_customer != null`.
- Use `Objects.nonNull` mainly where a method reference is required.
- Prefer ordinary loops and conditionals over streams or lambdas unless the newer construct is clearly easier to read.

## Constructors

Constructors should initialize state through setter methods whenever practical. Required dependencies should be validated during construction. Direct field assignment is acceptable when a setter would be unsafe or misleading.

## Exceptions

- Prefer checked exceptions for business and recoverable application errors.
- Throw specific exceptions, not generic `Exception`.
- Use custom checked exceptions for business and domain failures.
- Preserve the original cause when wrapping exceptions.
- Do not silently swallow exceptions.
- Every catch block should include a comment explaining why the exception is handled.
- Log exceptions before rethrowing when doing so helps trace the chain.

Avoid duplicate stack traces. Prefer logging the exception string when wrapping:

```java
LOGGER.error("Unable to load customer. Exception: {}", p_exception.toString());
```

## Logging

- Application code must log through an application-owned facade.
- Do not depend directly on Log4j, SLF4J, Logback, or `java.util.logging`.
- Declare one class-level logger:

```java
private static final ILogger LOGGER =
	LoggerFactory.getLogger(CustomerService.class);
```

Use log levels consistently:

- `DEBUG` for developer diagnostics.
- `INFO` for normal milestones.
- `WARN` for recoverable unexpected conditions.
- `ERROR` for failures that prevent an operation from completing.

`TRACE` is not required by default.

## Collections

- Declare collections using interfaces: `List`, `Map`, `Set`.
- Instantiate concrete implementations where needed: `new ArrayList<>()`, `new HashMap<>()`.
- Collection accessors should not return `null`.
- Lazy-initialize mutable collection attributes.
- Internal collections are mutable by default.
- Use immutable or unmodifiable collections when exposing fixed data that callers must not modify.
- Defensive copies are not required by default; use them when ownership or invariants require protection.

## Generics

- Never use raw types.
- Prefer concrete generic signatures.
- Avoid `? extends` and `? super` unless they solve a real API problem.
- Use conventional type names such as `T`, `K`, and `V` when obvious.
- Avoid complicated generic hierarchies.

## Loops

Preferred order:

1. Enhanced `for` loop.
2. Traditional indexed `for` loop when the index is required.
3. Iterator when modifying a collection during iteration.
4. Streams only when clearly more readable.

## Switch Statements

Use classic `switch` syntax:

```java
switch (customerType)
{
	case PREMIUM:
		processPremiumCustomer();
		break;

	case STANDARD:
		processStandardCustomer();
		break;

	default:
		processUnknownCustomer();
		break;
}
```

Always include `break` unless fall-through is intentional and documented. Always include a `default` case.

Avoid arrow switch expressions unless they are clearly more readable.

## Annotations

- Put one annotation per line above the class, method, field, or parameter.
- Keep multiple annotations in alphabetical order.
- Always use `@Override`.
- Pair `@Deprecated` with Javadoc explaining why, replacement, and planned removal when known.
- Use `@SuppressWarnings` only when the warning cannot reasonably be fixed.
- Keep suppressions narrow and explain why they are safe.
- Use one nullability annotation family if adopted.
- Avoid dependency injection annotations such as `@Autowired` and `@Inject`.
- Avoid custom annotations unless a real architectural need exists.

## Testing Style

Unit tests use explicit Arrange, Act, and Assert sections. See `JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md` for testing philosophy and `examples/testing/` for concrete examples.
