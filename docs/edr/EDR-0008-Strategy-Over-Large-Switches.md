# EDR-0008: Strategy Over Large Switches

## Decision

Use Strategy when behaviour genuinely varies by type, policy, or context. Prefer strategies and factories over large switches that select algorithms.

## Rationale

Large switches are hard to scan, test, and extend. Strategy moves varying behaviour into cohesive classes and makes the selection point explicit.

## Trade-Offs

Strategy introduces more classes. That is justified only when behaviours are meaningfully different or likely to evolve independently.

## Alternatives Considered

- Keep all algorithm branches in one switch.
- Use Strategy for every small conditional.

The standard rejects both extremes. Use Strategy when it solves a real design problem.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerDiscountStrategy.java`
- `../../examples/customer/src/main/java/com/example/customer/CustomerDiscountStrategyFactory.java`
