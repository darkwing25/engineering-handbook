# AGENTS.md

This repository is an Engineering Handbook. When working here, treat the handbook as a coherent set of standards rather than unrelated Markdown files.

## Required Reading Order

Before changing Java examples, standards, EDRs, or AI guidance, read:

1. `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`
2. `docs/ENGINEERING_LIFECYCLE_PRACTICES.md`
3. `docs/PROJECT_DOCUMENTATION_STANDARD.md`
4. `docs/PROJECT_ENGINEERING.md`
5. `docs/JAVA_ENGINEERING.md`
6. `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md`
7. `docs/JAVA_CODING_STANDARD.md`
8. `docs/ENGINEERING_DECISION_RECORDS.md`
9. Any relevant file under `docs/edr/`

For AI guidance edits, also read:

1. `ai/AI_ASSISTED_ENGINEERING_AUTHORING_WORKFLOW.md`
2. `ai/AI_EDITOR_GUIDELINES.md`
3. `ai/AI_OPERATIONAL_GOVERNANCE.md`
4. `ai/CODEX_PROJECT_PROMPT.md`
5. `ai/COPILOT_INSTRUCTIONS.md`

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
- Exactly one return statement at the end of every non-void method and no early return from void methods.
- Explicit constructor injection.
- Injectable factories and package-private test seams where appropriate.
- Class-level logger facade constants.
- Checked exceptions for business and persistence errors.
- Package-private or protected test seams where appropriate.
- Feature-oriented packages.
- Arrange, Act, Assert test structure.
- Package-private test classes and methods, with private test attributes unless an extension requires broader visibility.
- Javadoc on every test method identifying its exact happy path, edge condition, or failure trigger.
- Direct behavioural tests for every application-visible method, including constructors and accessors.
- Explicit functional-interface implementations instead of lambdas in Java examples.
- Direct return expressions for methods that would otherwise assign a local solely for the following return.
- Complete section banners in every Java type, including tests.

## Completion Criteria

Before declaring a handbook change complete:

- Confirm links point to real files.
- Confirm examples still match the documented standard.
- Confirm EDRs agree with the main documents.
- Run `mvn clean verify` for changed examples and the aggregate build.
- Run `git diff --check` and inspect the final diff.
- Report any deliberate deviation from the standard.
