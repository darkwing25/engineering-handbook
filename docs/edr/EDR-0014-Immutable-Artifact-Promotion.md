# EDR-0014: Immutable Artifact Promotion

## Decision

Build a deployable artifact once and promote that exact artifact through environments. Supply environment-specific configuration externally and validate it at startup.

## Rationale

Rebuilding for each environment creates different code from the code already tested. Immutable promotion preserves the evidence gathered in lower environments and separates application content from deployment configuration.

Version non-secret schemas, defaults, validation rules, and example templates with the source. Keep actual environment values and secrets external.

## Trade-Offs

The artifact must support external configuration, and the delivery platform must retain and identify immutable versions. Those constraints are preferable to untested environment-specific rebuilds.

## Alternatives Considered

- Compile a different artifact for each environment.
- Embed production configuration in source or packaging.

Both weaken provenance and make rollback less reliable.
