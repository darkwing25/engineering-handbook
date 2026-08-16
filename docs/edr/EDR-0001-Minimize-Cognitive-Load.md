# EDR-0001: Minimize Cognitive Load

## Decision

Good software should minimize the cognitive effort required to understand, debug, test, and maintain it.

## Rationale

The future maintainer is intelligent but unfamiliar with the code. The code should not require that person to reconstruct hidden behaviour, decode clever syntax, search for dependencies, or infer structure from inconsistent formatting.

This principle guides the whole handbook: explicit dependencies, predictable class layout, one return statement, classic switch syntax, feature-oriented packages, straightforward tests, and restrained use of abstractions.

## Trade-Offs

Some code will be longer than the most compact possible version. That cost is acceptable when the result is easier to read and safer to maintain.

## Alternatives Considered

- Optimize for brevity.
- Prefer modern language features by default.
- Let each developer choose their own style.

These were rejected because they increase maintenance cost and make AI-generated code less predictable.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerService.java`
- `../../examples/testing/src/test/java/com/example/testing/CustomerFileWriterTest.java`
