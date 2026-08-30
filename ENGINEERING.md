# Engineering

This file is the engineering entry point for the Engineering Handbook repository. It demonstrates the root-level document required by the [Project Documentation Standard](docs/PROJECT_DOCUMENTATION_STANDARD.md).

## Purpose

The repository maintains language-independent engineering principles, lifecycle practices, language-specific standards, AI guidance, decision records, and executable examples.

## Governing Guidance

Changes must preserve this order of authority:

1. The explicit task or approved engineering decision.
2. [Software Design Philosophy](docs/SOFTWARE_DESIGN_PHILOSOPHY.md).
3. [Engineering Lifecycle Practices](docs/ENGINEERING_LIFECYCLE_PRACTICES.md).
4. Relevant language-specific architecture and coding standards.
5. Relevant EDRs and examples.

Conflicts are reported and resolved explicitly. They are never silently worked around.

## Technology

The handbook is Markdown. Java examples:

- target Java 25,
- use Maven,
- use the latest deliberately adopted stable JUnit release,
- and enforce 80% line and branch coverage.

The prose remains version-independent. Every adopting project declares its own language and runtime versions.

Repository-specific implementation details are in [Project Engineering](docs/PROJECT_ENGINEERING.md) and [Java Engineering](docs/JAVA_ENGINEERING.md).

## Build and Verification

Run the complete Java example suite from the repository root:

```shell
mvn clean verify
```

Before completing a documentation change:

- confirm links resolve,
- confirm EDRs agree with the main documents,
- confirm examples demonstrate the stated rules,
- run `git diff --check`,
- and inspect the final diff and repository status.

## Documentation

Documentation changes in the same pull request as the behaviour they describe. Documentation drift is a defect.

The repository is the authoritative source. External material may supplement it, but this repository must identify the external material's purpose, location, owner, and access procedure without storing secrets.

## AI Assistance

AI work follows [AI Operational Governance](ai/AI_OPERATIONAL_GOVERNANCE.md). AI-generated content is reviewed to the same standard as human-generated content. A human remains accountable for every accepted change.
