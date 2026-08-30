# Codex Project Prompt

You are working in a language-independent engineering handbook with Java examples, governed by these documents:

1. `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`
2. `docs/ENGINEERING_LIFECYCLE_PRACTICES.md`
3. `docs/PROJECT_DOCUMENTATION_STANDARD.md`
4. `docs/PROJECT_ENGINEERING.md`
5. `docs/JAVA_ENGINEERING.md`
6. `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md`
7. `docs/JAVA_CODING_STANDARD.md`
8. `docs/CODE_REVIEW_CHECKLIST.md`
9. `docs/ENGINEERING_DECISION_RECORDS.md`
10. `ai/AI_OPERATIONAL_GOVERNANCE.md`

Read and follow them before creating, modifying, reviewing, or refactoring Java code.

## Order of Precedence

Apply guidance in this order:

1. The explicit user request.
2. The Software Design Philosophy.
3. Engineering Lifecycle Practices.
4. Project and language engineering documentation.
5. Java Architecture and Development Practices.
6. Java Coding Standard.
7. Relevant EDRs.
8. Existing project conventions that do not conflict with the handbook.

Do not silently disregard a rule. If a task requires an exception, explain the conflict and use the smallest reasonable deviation.

## Engineering Approach

Design software for readability, maintainability, debuggability, testability, and long-term support.

Prefer:

- explicit code over implicit behaviour,
- straightforward object-oriented Java over clever or heavily functional Java,
- clear control flow over compressed expressions,
- direct return expressions instead of local variables that exist only for the following return,
- constructor injection for required collaborators and controlled replacement for class-owned defaults,
- injectable factories when orchestration selects behaviour,
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

Test every application-visible method directly, including constructors, accessors, and mechanically generated APIs. Test package-private business helpers in addition to, never instead of, their public entry points. Use package-private JUnit test classes and methods. Give every test method Javadoc that identifies its exact happy path, edge condition, or failure trigger. Use `assertThrows` when it expresses the expected failure clearly, and provide functional-interface operations explicitly rather than as lambdas.

## Operational Boundaries

Classify work through the three-tier risk model in `ai/AI_OPERATIONAL_GOVERNANCE.md`. Before agentic execution, define objective, permitted tools and data, resource limits, boundaries, stop conditions, checkpoints, and recovery. High-risk work requires independent AI critique and explicit human approval. Never put secrets into prompts or tool inputs.

## Completion Report

When done, report:

- what changed,
- why the design was chosen,
- which standards or EDRs guided the change,
- which tests were run,
- and any assumptions or deviations.
