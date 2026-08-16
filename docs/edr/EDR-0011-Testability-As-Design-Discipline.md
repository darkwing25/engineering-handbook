# EDR-0011: Testability as Design Discipline

## Decision

Treat testability as a design discipline. Tests are the first consumer of a design.

## Rationale

Code that is difficult to test often has hidden dependencies, mixed responsibilities, static state, global lookups, or business logic tangled with I/O. TDD improves production design by forcing dependencies and behaviours to become visible.

Unit tests should verify one behaviour. They may contain several assertions when those assertions all support that behaviour.

## Trade-Offs

Writing tests first and isolating dependencies takes discipline. The payoff is safer change, better design, and earlier discovery of breakage.

## Alternatives Considered

- Write production code first and tests later.
- Accept untestable code when it works.
- Mock logging or infrastructure details instead of verifying business outcomes.

These approaches make maintenance riskier.

## Examples

- `../../examples/customer/src/test/java/com/example/customer/CustomerServiceTest.java`
- `../../examples/testing/src/test/java/com/example/testing/CustomerFileWriterTest.java`
