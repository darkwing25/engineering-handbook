# EDR-0004: Constructor Injection and Fail Fast Validation

## Decision

Required dependencies are passed through constructors and validated immediately. A class-owned collaborator with a safe default may be initialized lazily and replaced through a narrowly visible setter. A service that selects behaviour per operation holds an injectable factory or resolver and resolves the collaborator on each public call.

## Rationale

Dependencies should be explicit and traceable. A missing required dependency should fail during construction, not many calls later. Controlled replacement keeps business logic testable without making infrastructure setters part of the public API.

A setter called by a constructor is package-private or protected and `final` so construction cannot dispatch into incomplete subclass state.

## Trade-Offs

Constructor signatures expose dependencies directly. That is intentional. If there are too many constructor dependencies, the class probably has too many responsibilities.

## Alternatives Considered

- Field injection.
- Service locators.
- Hidden framework autowiring.
- Public setters for internal dependencies.
- Constructing side-effecting collaborators inside business methods.
- Static factories when selection behaviour needs substitution.

These hide construction, weaken validity, or make testing harder.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerService.java`
- `../../examples/customer/src/test/java/com/example/customer/CustomerServiceTest.java`
