# EDR-0004: Constructor Injection and Fail Fast Validation

## Decision

Required dependencies are passed through constructors and validated immediately. Defensive getter checks may remain as a second line of protection.

## Rationale

Dependencies should be explicit and traceable. A missing required dependency should fail during construction, not many calls later. This improves testability and makes object validity clear.

## Trade-Offs

Constructor signatures expose dependencies directly. That is intentional. If there are too many constructor dependencies, the class probably has too many responsibilities.

## Alternatives Considered

- Field injection.
- Service locators.
- Hidden framework autowiring.
- Public setters for all dependencies.

These hide object construction and make testing harder.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerService.java`
- `../../examples/customer/src/test/java/com/example/customer/CustomerServiceTest.java`
