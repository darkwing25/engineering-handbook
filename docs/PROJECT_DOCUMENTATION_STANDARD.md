# Project Documentation Standard

Engineering knowledge must be predictable to find and must live with the project whenever practical.

## Required Structure

Every project provides:

```text
ENGINEERING.md
docs/
    PROJECT_ENGINEERING.md
    <LANGUAGE>_ENGINEERING.md
```

Use one language file for each language in a multi-language repository, for example `JAVA_ENGINEERING.md`, `JAVASCRIPT_ENGINEERING.md`, or `POWERSHELL_ENGINEERING.md`.

## Root `ENGINEERING.md`

The root file is the first engineering entry point for people and AI. It contains:

- the project's purpose and scope,
- links to governing handbook material,
- the required reading order,
- the primary build and test commands,
- the location of project and language documents,
- and the rules for completing and reporting changes.

Keep it concise enough to remain useful as an entry point.

## `docs/PROJECT_ENGINEERING.md`

The project-wide document records:

- owning team, primary and backup contacts, escalation, and project code,
- repository and branching workflow,
- supported environments,
- build, test, coverage, static-analysis, lint, and scanning commands,
- CI/CD and scheduled health builds,
- artifact packaging, promotion, release, rollback, and roll-forward,
- configuration and secret sources,
- dependency update and vulnerability-monitoring practices,
- data classifications, trust boundaries, and security model,
- backups, recovery objectives, and restoration tests,
- observability, dashboards, alerts, and runbooks,
- external systems and access procedures,
- approved deviations and known risks,
- and archival or restoration information when applicable.

Do not put secrets in this file.

## Language Engineering Documents

Each `docs/<LANGUAGE>_ENGINEERING.md` records:

- the language and runtime version,
- build and package-management tools and pinned versions,
- language-specific architecture and coding standards,
- test framework and coverage implementation,
- generated-code policy,
- language-specific static analysis and formatting,
- entry points and package or module organization,
- and deliberate deviations from the central language standard.

The central handbook remains version-independent. Projects and examples declare their actual versions.

## Deviations and Feedback

Document a deviation locally when it is made, including its reason, owner, risk, and review condition. Contribute reusable lessons to the central handbook. Leave genuinely project-specific constraints in the project rather than turning them into universal rules.

## Documentation Quality

Documentation changes with behaviour. Verify commands, links, file names, owners, and external references during review. Missing or stale documentation is a defect.
