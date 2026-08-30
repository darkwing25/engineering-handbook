# EDR-0006: Checked Exceptions and Preserved Causes

## Decision

Use checked exceptions for anticipated, recoverable business and persistence errors. Use unchecked exceptions for programming errors, API misuse, and broken invariants. Throw specific exceptions and preserve the original cause when wrapping.

## Rationale

Checked exceptions make error contracts visible and easier to test. Specific exceptions communicate what failed. Preserved causes keep diagnostic context intact.

A catch block explains its reasoning when the handling is not self-evident. A concise comment should identify an intentional exception translation at an application boundary when that rationale helps the reader understand why a different exception is thrown. A comment should explain the decision rather than merely restate the syntax.

## Trade-Offs

Checked exceptions require more method signatures and handling code. That is acceptable when the failure is part of the contract.

## Alternatives Considered

- Runtime exceptions for most failures.
- Generic `Exception`.
- Wrapping without causes.

These reduce immediate ceremony but hide important behaviour.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerLoadException.java`
- `../../examples/customer/src/main/java/com/example/customer/CustomerRepository.java`
