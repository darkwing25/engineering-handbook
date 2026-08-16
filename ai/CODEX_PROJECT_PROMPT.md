# Codex Project Prompt

You are working in a Java engineering handbook governed by these documents:

1. `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`
2. `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md`
3. `docs/JAVA_CODING_STANDARD.md`
4. `docs/CODE_REVIEW_CHECKLIST.md`
5. `docs/ENGINEERING_DECISION_RECORDS.md`

Read and follow them before creating, modifying, reviewing, or refactoring Java code.

## Order of Precedence

Apply guidance in this order:

1. The explicit user request.
2. The Software Design Philosophy.
3. Java Architecture and Development Practices.
4. Java Coding Standard.
5. Relevant EDRs.
6. Existing project conventions that do not conflict with the handbook.

Do not silently disregard a rule. If a task requires an exception, explain the conflict and use the smallest reasonable deviation.

## Engineering Approach

Design software for readability, maintainability, debuggability, testability, and long-term support.

Prefer:

- explicit code over implicit behaviour,
- straightforward object-oriented Java over clever or heavily functional Java,
- clear control flow over compressed expressions,
- constructor injection over hidden wiring,
- factories and facades when they reduce coupling,
- strategies when behaviours genuinely vary,
- checked exceptions for business and recoverable application failures,
- logging through an application-owned facade,
- deterministic tests,
- and focused refactoring with a clear purpose.

Avoid:

- unnecessary streams and lambdas,
- annotation-heavy designs,
- Spring-style field injection unless the project explicitly requires it,
- hidden service locators,
- broad rewrites for small tasks,
- modernizing code merely to use newer syntax,
- unnecessary abstractions,
- direct coupling to replaceable infrastructure libraries,
- and patterns that do not solve a real engineering problem.

## Before Changing Code

Determine:

- what behaviour is required,
- what tests demonstrate it,
- which class owns the responsibility,
- whether the design remains testable,
- whether dependencies are explicit,
- whether resource and transaction ownership are clear,
- and which standards or EDRs apply.

## Testing

Use TDD when practical:

1. Write or identify the test.
2. Confirm the expected failure when possible.
3. Implement the smallest clear change.
4. Run relevant tests.
5. Refactor while preserving behaviour.
6. Run tests again.

Do not weaken assertions to make a failing test pass.

## Completion Report

When done, report:

- what changed,
- why the design was chosen,
- which standards or EDRs guided the change,
- which tests were run,
- and any assumptions or deviations.
