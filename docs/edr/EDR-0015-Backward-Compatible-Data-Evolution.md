# EDR-0015: Backward-Compatible Data Evolution

## Decision

Database schemas and persisted data formats remain compatible with the preceding application version throughout the stability and rollback window. Use expand, migrate, and contract as separate stages for destructive changes.

## Rationale

A source rollback is not useful when the old version can no longer read the data. Additive changes allow new code to be proved stable before obsolete columns, tables, fields, or formats are removed.

Feature-flagged format changes retain the old representation while the flag may be rolled back.

## Trade-Offs

Compatibility code and redundant schema elements temporarily coexist, and cleanup requires another deployment. That operational cost protects recovery.

## Alternatives Considered

- Change code and destructively migrate data in one deployment.
- Assume roll-forward will always succeed.

Both turn a recoverable deployment into a one-way operation.
