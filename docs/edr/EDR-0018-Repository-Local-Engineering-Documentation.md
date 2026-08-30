# EDR-0018: Repository-Local Engineering Documentation

## Decision

Every project provides a root `ENGINEERING.md`, a `docs/PROJECT_ENGINEERING.md`, and one `docs/<LANGUAGE>_ENGINEERING.md` per implementation language. The repository is the authoritative engineering entry point.

External documentation may supplement the repository, but the repository records its purpose, location, owner, and access procedure without storing secrets.

## Rationale

Developers and AI assistants must be able to find build, testing, architecture, operational, ownership, and recovery knowledge without relying on tribal memory or knowing which external system to search.

Project documents hold concrete versions and tools; the central handbook holds durable principles. Documentation, examples, runbooks, and affected decisions change with the behaviour they describe.

## Trade-Offs

Repository-local documentation must be maintained and may summarize material that also exists elsewhere. This controlled duplication is preferable to undiscoverable or inaccessible knowledge.

## Alternatives Considered

- Store all engineering documentation in a wiki.
- Use only one generic project file for multi-language systems.

Both make relevant language guidance or recovery information harder to locate reliably.

## Related Guidance

- `../PROJECT_DOCUMENTATION_STANDARD.md`
