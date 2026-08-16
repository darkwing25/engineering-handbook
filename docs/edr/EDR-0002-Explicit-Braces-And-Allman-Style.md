# EDR-0002: Explicit Braces and Allman Style

## Decision

Use Allman braces and always include braces around control-structure bodies, even when the body has one statement.

## Rationale

Explicit braces remove ambiguity and make edits safer. Allman style gives visual structure when scanning a file. The goal is not fewer lines; the goal is readable, predictable control flow.

## Trade-Offs

The style uses more vertical space than compact brace styles. That is acceptable because it improves scanning and reduces mistakes during maintenance.

## Alternatives Considered

- Omit braces for single-line bodies.
- Use inline or K&R brace style.

These are common styles, but they do not match the handbook's emphasis on visual consistency and explicit structure.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/CustomerService.java`
