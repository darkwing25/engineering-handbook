# EDR-0010: Explicit Resource and Transaction Ownership

## Decision

Resource ownership and transaction boundaries must be explicit. Prefer `try-with-resources`. Service methods normally own business transaction boundaries.

## Rationale

Resources should be closed promptly in the smallest reasonable scope. A method must not close a resource supplied by its caller unless ownership is explicitly transferred.

Transactions belong where the full business operation is understood. Repositories may participate in a transaction, but they should not commit or roll back a larger service operation independently.

## Trade-Offs

Explicit transaction code can be more verbose than hidden framework transactions. The visibility is valuable because transaction ownership affects correctness.

## Alternatives Considered

- Hide transaction ownership in unrelated repository methods.
- Defer cleanup without an ownership contract.
- Rely on finalizers or framework magic.

These obscure lifecycle responsibility.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerRepository.java`
