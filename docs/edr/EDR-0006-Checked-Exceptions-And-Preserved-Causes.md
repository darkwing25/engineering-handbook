# EDR-0006: Checked Exceptions and Preserved Causes

## Decision

Prefer checked exceptions for business and recoverable application errors. Throw specific custom exceptions and preserve the original cause when wrapping.

## Rationale

Checked exceptions make error contracts visible and easier to test. Specific exceptions communicate what failed. Preserved causes keep diagnostic context intact.

Catch blocks should explain why the exception is handled so future maintainers understand the intent.

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
