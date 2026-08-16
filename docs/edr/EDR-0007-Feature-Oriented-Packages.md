# EDR-0007: Feature-Oriented Packages

## Decision

Organize Java packages by feature or business domain rather than global technical layer.

## Rationale

Feature-oriented packages keep related models, services, repositories, exceptions, and tests close together. This improves cohesion and makes it easier to understand a feature as a unit.

Package size is a design signal. When a package grows beyond roughly 10-12 classes, review whether responsibilities have drifted.

## Trade-Offs

Some technical roles repeat across packages. That is acceptable because the feature boundary remains clearer.

## Alternatives Considered

- Global `service`, `repository`, `model`, and `exception` packages.
- Deep package hierarchies.

These can scatter a feature across the codebase.

## Examples

- `../../examples/customer/src/main/java/com/example/customer/`
- `../../examples/customer/src/test/java/com/example/customer/`
