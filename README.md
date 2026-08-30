# Engineering Handbook

This repository defines a language-independent engineering handbook for software that people can understand, test, operate, and maintain over time. Java is the first language-specific standard because it is the language for which the initial conventions are most fully established. Other language standards may be added without forcing Java's object model or syntax onto them.

The central thesis is simple: software is written for people first and computers second. Correct execution is mandatory, but future developers must be able to understand the behaviour and architecture without becoming lost in implementation minutiae.

## Start Here

Read the handbook in this order:

1. [Software Design Philosophy](docs/SOFTWARE_DESIGN_PHILOSOPHY.md)
2. [Engineering Lifecycle Practices](docs/ENGINEERING_LIFECYCLE_PRACTICES.md)
3. [Project Documentation Standard](docs/PROJECT_DOCUMENTATION_STANDARD.md)
4. [Java Architecture and Development Practices](docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md)
5. [Java Coding Standard](docs/JAVA_CODING_STANDARD.md)
6. [Code Review Checklist](docs/CODE_REVIEW_CHECKLIST.md)
7. [Engineering Decision Records](docs/ENGINEERING_DECISION_RECORDS.md)

The root [ENGINEERING.md](ENGINEERING.md) is the engineering entry point for this repository itself.

## Repository Structure

```text
ai/          AI authoring, editing, coding, and operational-governance rules
docs/        philosophy, lifecycle, Java standards, review guidance, and EDRs
examples/    independently buildable Java teaching projects
```

The `ai/` directory contains durable guidance for Codex, Copilot, and other approved AI assistants. AI-generated work is held to the same engineering standard as human-written work and remains subject to accountable human review.

## Runnable Examples

The Java examples target Java 25 and use Maven. Each example has its own build, and the repository root provides an aggregate build.

```shell
mvn verify
```

The build compiles every example, runs its tests, and enforces at least 80% line and branch coverage. The example baseline changes only when a later Java LTS release is deliberately adopted.

## Applying the Handbook to a Project

Every adopting project provides:

- a root `ENGINEERING.md` entry point,
- `docs/PROJECT_ENGINEERING.md` for project-wide implementation details,
- and one `docs/<LANGUAGE>_ENGINEERING.md` file per language in use.

The central handbook supplies durable principles. Each project documents its concrete versions, tools, commands, environments, risks, owners, and approved deviations. Reusable lessons discovered through a project are contributed back to the central handbook; one-off constraints remain local.

## Community and Licensing

Handbook prose and documentation are licensed under the Creative Commons Attribution 4.0 International License. See [LICENSE](LICENSE).

Code examples under `examples/` are licensed under the MIT License. See [examples/LICENSE](examples/LICENSE).

Corrections and substantial proposals are welcome. See [CONTRIBUTING.md](CONTRIBUTING.md).

## Status

This handbook remains under deliberate review. Published guidance should change through reviewable updates and EDRs, not through undocumented convention drift.
