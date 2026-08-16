# EDR-0012: Refactoring With Scope Visibility

## Decision

Refactoring should improve design, readability, testability, or maintainability while keeping scope visible.

Preferred order:

1. Add characterization or regression tests.
2. Refactor while preserving behaviour.
3. Implement the requested change.
4. Run tests after each stage.

## Rationale

Leaving code better than it was found is valuable, but broad rewrites can surprise clients and reviewers. The issue is often not the refactoring intent; it is invisible scope growth.

## Trade-Offs

Some poor foundations require broad redesign. That should be explicit, estimated, approved, and tracked.

## Alternatives Considered

- Never refactor during feature work.
- Refactor broadly whenever ugly code is encountered.

Both are too rigid. The standard distinguishes required, adjacent, and strategic refactoring.

## Examples

- `../../docs/CODE_REVIEW_CHECKLIST.md`
