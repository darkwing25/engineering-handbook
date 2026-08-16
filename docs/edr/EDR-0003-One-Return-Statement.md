# EDR-0003: One Return Statement

## Decision

Prefer one return statement per method and avoid early returns.

## Rationale

A single exit point makes methods easier to debug and reason about. Future maintainers can inspect the initialized result variable and follow all paths to one return.

## Trade-Offs

Some guard-clause-heavy methods may be shorter with early returns. The standard favours debuggability and predictable flow over compactness.

## Alternatives Considered

- Use early returns for guard clauses.
- Return directly from every branch.

These can be appropriate in some codebases, but this handbook favours a single visible method outcome.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerRepository.java`
- `../../examples/customer/src/main/java/com/example/customer/CustomerService.java`
