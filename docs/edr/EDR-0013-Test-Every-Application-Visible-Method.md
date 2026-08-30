# EDR-0013: Test Every Application-Visible Method

## Decision

Every application-visible method receives direct behavioural unit-test coverage, including constructors, getters, setters, and mechanically generated APIs. Compiler-created synthetic internals outside the observable contract are not direct test targets. Every unit-test method has Javadoc that identifies the specific happy path, alternate path, edge condition, or failure trigger it verifies.

## Rationale

An accessor can use the wrong attribute or map key. A generator can faithfully produce code from an incorrect mapping or configuration. Neither simplicity nor mechanical generation proves behaviour.

Direct tests make the intended contract explicit and localize defects that might otherwise appear much later in an unrelated workflow. Test-method Javadoc makes the selected path and its triggering conditions visible before a reader decodes the setup. An 80% line and branch threshold supports this discipline but does not replace meaningful assertions.

## Trade-Offs

The suite contains tests for apparently simple methods, and each test carries a short behavioural explanation. That material takes more time to maintain. The cost is accepted because a small undetected mapping error or misunderstood path can consume far more diagnostic effort.

## Alternatives Considered

- Test accessors only through larger business workflows.
- Exempt generated code.
- Depend on coverage percentage alone.

These approaches can hide precisely the small wiring and mapping defects the rule is intended to expose.

## Examples

- `../../examples/customer/src/test/java/com/example/customer/CustomerTest.java`
- `../../examples/customer/src/test/java/com/example/customer/CustomerServiceTest.java`
