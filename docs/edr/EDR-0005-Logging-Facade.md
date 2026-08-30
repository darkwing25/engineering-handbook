# EDR-0005: Logging Facade

## Decision

Application code logs through an application-owned facade rather than depending directly on a specific logging backend. A failure is logged once at the boundary that handles or reports it.

## Rationale

Logging libraries change. Application code should not require a mass refactor when moving from Log4j to SLF4J, Logback, `java.util.logging`, or another backend.

The facade also centralizes cross-cutting logging behaviour such as formatting, routing, timing, metrics, and auditing. Logging and rethrowing the same failure at every layer creates duplicate stack traces and hides the useful signal. Lower layers preserve causes and log only unique context that cannot be reconstructed by the handling boundary.

## Trade-Offs

A facade adds a small abstraction. The benefit is project-wide decoupling and one place to change logging behaviour.

## Alternatives Considered

- Use a backend logger directly in every class.
- Decorate logger objects throughout application code.
- Log and rethrow the same exception at every layer.

Both spread infrastructure decisions across the project.

## Examples

- `../../examples/logging/src/main/java/com/example/logging/ILogger.java`
- `../../examples/logging/src/main/java/com/example/logging/LoggerFactory.java`
