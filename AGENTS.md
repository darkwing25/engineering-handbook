# AGENTS.md

This repository is an Engineering Handbook. When working here, treat the handbook as a coherent set of standards rather than unrelated Markdown files.

## Required Reading Order

Before changing Java examples, standards, EDRs, or AI guidance, read:

1. `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`
2. `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md`
3. `docs/JAVA_CODING_STANDARD.md`
4. `docs/ENGINEERING_DECISION_RECORDS.md`
5. Any relevant file under `docs/edr/`

For AI guidance edits, also read:

1. `ai/AI_ASSISTED_ENGINEERING_AUTHORING_WORKFLOW.md`
2. `ai/AI_EDITOR_GUIDELINES.md`
3. `ai/CODEX_PROJECT_PROMPT.md`
4. `ai/COPILOT_INSTRUCTIONS.md`

## Editorial Rules

- Preserve engineering intent over stylistic improvement.
- Do not modernize recommendations simply because newer Java features exist.
- Explain what and why, not only what.
- Distinguish principles from preferences.
- Ask when a proposed change would alter philosophy.
- Keep EDRs numbered, one topic per file, and short enough to review easily.
- Link EDRs to examples when an example exists.
- Do not create stubs. If a file is added, make it useful.

## Java Example Rules

Java examples should follow the handbook:

- Allman braces.
- Tabs for indentation.
- Mandatory braces.
- One return statement per method.
- Explicit constructor injection.
- Class-level logger facade constants.
- Checked exceptions for business and persistence errors.
- Package-private or protected test seams where appropriate.
- Feature-oriented packages.
- Arrange, Act, Assert test structure.

## Completion Criteria

Before declaring a handbook change complete:

- Confirm links point to real files.
- Confirm examples still match the documented standard.
- Confirm EDRs agree with the main documents.
- Run any available tests for changed examples.
- Report any deliberate deviation from the standard.
