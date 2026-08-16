# Engineering Handbook

This repository captures a pragmatic engineering standard for maintainable Java systems. It is not only a style guide. It records the design philosophy, architecture guidance, coding conventions, review practices, decision records, AI guidance, and examples needed to write software that another developer can understand, test, debug, and maintain years later.

The core philosophy is simple: software is written for people first and computers second. The compiler needs the program to be correct; future maintainers need it to be clear.

## Repository Structure

```text
ai/
    AI_ASSISTED_ENGINEERING_AUTHORING_WORKFLOW.md
    AI_EDITOR_GUIDELINES.md
    CODEX_PROJECT_PROMPT.md
    COPILOT_INSTRUCTIONS.md
docs/
    CODE_REVIEW_CHECKLIST.md
    ENGINEERING_DECISION_RECORDS.md
    JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md
    JAVA_CODING_STANDARD.md
    SOFTWARE_DESIGN_PHILOSOPHY.md
    edr/
examples/
    customer/
    logging/
    testing/
AGENTS.md
README.md
```

## How to Use This Handbook

Start with `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`. It is the north star for every other document. Then read `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md` for design guidance and `docs/JAVA_CODING_STANDARD.md` for concrete Java conventions.

Use `docs/CODE_REVIEW_CHECKLIST.md` before pull requests and during reviews. Use `docs/ENGINEERING_DECISION_RECORDS.md` to find the reason behind a standard. Each EDR is intentionally one topic and roughly one page.

The `examples/` directory shows the standards in code:

- `examples/customer/` demonstrates feature-oriented packages, constructor injection, checked exceptions, factories, services, repositories, and tests.
- `examples/logging/` demonstrates a logging facade that isolates application code from any particular backend.
- `examples/testing/` demonstrates Arrange/Act/Assert tests, explicit Mockito setup, checked exception assertions, temporary directories, and deterministic test design.

The `ai/` directory contains durable guidance for Codex, Copilot, and future AI coding assistants. AI tools should read the philosophy first, then the architecture practices, then the Java coding standard.

## Draft Status

This package is Draft 1.0. It is fully populated from the established decisions, but it should still be reviewed document by document before being treated as final policy.
