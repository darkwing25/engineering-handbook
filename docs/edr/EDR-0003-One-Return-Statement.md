# EDR-0003: One Return Statement

## Decision

Every non-void method has exactly one return statement at its end. A void method uses its single natural exit and does not use an explicit early `return`.

## Rationale

A single exit point makes methods easier to scan, debug, and reason about. When several paths produce a value, future maintainers can inspect the result variable and follow every path to one visible outcome. When a method only produces one expression, that expression is returned directly at the single exit; a throwaway local variable does not improve visibility. This is a deliberate readability convention, not a claim about just-in-time compiler performance.

## Trade-Offs

Some guard-clause-heavy methods would be shorter with early returns. The standard accepts the additional structure in exchange for predictable control flow. It does not require a result variable in a method that has no alternate path or meaningful intermediate state.

## Alternatives Considered

- Use early returns for guard clauses.
- Return directly from every branch.

These can be appropriate in other codebases, but they are not part of this Java standard.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerRepository.java`
- `../../examples/customer/src/main/java/com/example/customer/CustomerService.java`
