# Copilot Instructions

Generate Java that follows this repository's engineering handbook.

## Required Context

When suggesting Java code, align with:

- `docs/SOFTWARE_DESIGN_PHILOSOPHY.md`
- `docs/JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md`
- `docs/JAVA_CODING_STANDARD.md`
- relevant files under `docs/edr/`

## Code Style

- Use Allman braces.
- Use tabs for indentation.
- Always use braces.
- Prefer one return statement per method.
- Prefer direct `!= null` checks in ordinary code.
- Use `p_` prefixes for parameters.
- Use `m_` prefixes for attributes.
- Use `UPPER_SNAKE_CASE` for constants.
- Keep parameter lists on one line; if they grow too long, suggest a design change.
- Keep class sections in the standard order.

## Design Style

- Prefer explicit constructor injection.
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
- Name normal unit tests `test_methodName`.
- Add suffixes for specific branches or edge cases.
- Prefer explicit Mockito mock creation.
- Mock external systems and slow or nondeterministic dependencies.
- Do not mock logging unless logging itself is the feature.
- Use explicit try/catch when testing exceptions.

## Avoid

- Streams or lambdas when loops are clearer.
- Arrow switch expressions as the default.
- Heavy annotations.
- Unnecessary wildcard generics.
- Runtime exceptions for business errors.
- Direct dependency on a specific logging backend.
