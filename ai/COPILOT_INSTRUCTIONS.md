# Copilot Instructions

Generate Java that follows this repository's engineering handbook.

## Required Context

When suggesting Java code, align with:

- `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`
- `docs/ENGINEERING_LIFECYCLE_PRACTICES.md`
- `docs/PROJECT_ENGINEERING.md`
- `docs/JAVA_ENGINEERING.md`
- `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md`
- `docs/JAVA_CODING_STANDARD.md`
- `ai/AI_OPERATIONAL_GOVERNANCE.md`
- relevant files under `docs/edr/`

## Code Style

- Use Allman braces.
- Use tabs for indentation.
- Always use braces.
- Use exactly one return statement at the end of each non-void method and no early return in a void method.
- Return a single expression directly instead of assigning it to a local variable solely for the following return.
- Prefer direct `!= null` checks in ordinary code.
- Use `p_` prefixes for parameters.
- Use `m_` prefixes for attributes.
- Use `UPPER_SNAKE_CASE` for constants.
- Keep parameter lists on one line; if they grow too long, suggest a design change.
- Include every standard section banner in every Java type, including tests.

## Design Style

- Prefer explicit constructor injection.
- Use a controlled package-private replacement setter only for a safe class-owned default.
- Make constructor-invoked setters non-overridable.
- Use an injectable factory when a service selects a strategy on each public call.
- Avoid field injection and hidden framework wiring.
- Keep loggers as class-level constants.
- Use factories when construction should be centralized.
- Use strategies when behaviour varies.
- Keep adapters thin.
- Put business decisions in services, strategies, or domain objects.
- Avoid singletons unless uniqueness is inherent.
- Use patterns only when they solve a real problem.

## Tests

- Use Arrange, Act, Assert comments.
- Keep test classes and methods package-private.
- Name normal unit tests `test_methodName`.
- Add suffixes for specific branches or edge cases.
- Add Javadoc to every test method identifying its happy path, edge condition, or failure trigger.
- Prefer explicit Mockito mock creation.
- Mock external systems and slow or nondeterministic dependencies.
- Do not mock logging unless logging itself is the feature.
- Use `assertThrows` when it expresses an exception test clearly.
- Expand functional-interface operations explicitly instead of using lambdas.
- Test every application-visible method, including constructors, accessors, and generated APIs.
- Test a package-private helper in addition to its public entry point, never instead of it.

## Avoid

- Streams or lambdas when loops are clearer.
- Arrow switch expressions as the default.
- Heavy annotations.
- Unnecessary wildcard generics.
- Runtime exceptions for business errors.
- Direct dependency on a specific logging backend.
