# EDR-0009: Thin Adapters

## Decision

Adapters translate; they do not decide. Keep business logic in services, strategies, or domain objects.

## Rationale

Adapters isolate external APIs, vendor models, protocols, and legacy interfaces. Mixing business rules into adapters muddies responsibility and makes integrations harder to replace.

## Trade-Offs

Keeping adapters thin may require an additional service or strategy class. That separation improves testability and replacement.

## Alternatives Considered

- Put business rules directly into adapter methods.
- Let vendor models flow through application code.

Both increase coupling and make replacement harder.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerRepository.java`
