# Code Review Checklist

Use this checklist before opening a pull request and during review. Reviews should improve the code, teach the developer, and enforce agreed standards.

Prefer conversation for meaningful design feedback. Written comments should explain the reason, not only the command.

## Correctness

- [ ] Does the change solve the requested problem?
- [ ] Are edge cases handled?
- [ ] Are invalid inputs handled deliberately?
- [ ] Are exceptions specific and meaningful?
- [ ] Are original exception causes preserved?
- [ ] Are return values correct for success, absence, and failure cases?

## Design

- [ ] Does each class have one clear responsibility?
- [ ] Is the responsibility in the right class or layer?
- [ ] Are abstractions justified by current requirements?
- [ ] Are patterns solving real engineering problems?
- [ ] Are business decisions kept out of adapters and facades?
- [ ] Are large switches replaced by strategies when behaviour genuinely varies?
- [ ] Are singletons avoided unless uniqueness is inherent?

## Dependencies

- [ ] Are dependencies explicit?
- [ ] Is constructor injection used for required dependencies?
- [ ] Are required dependencies validated during construction?
- [ ] Are hidden service locators and global lookups avoided?
- [ ] Is application code decoupled from replaceable infrastructure libraries?

## Testability

- [ ] Is the code easy to unit test?
- [ ] Are external systems isolated behind mockable interfaces?
- [ ] Are tests deterministic?
- [ ] Are test databases and files isolated from production and developer machines?
- [ ] Are Arrange, Act, and Assert sections clear?
- [ ] Does each test verify one behaviour?
- [ ] Are flaky tests treated as defects?

## Resource and Transaction Ownership

- [ ] Are resources closed in the smallest reasonable scope?
- [ ] Is `try-with-resources` used for `AutoCloseable` resources?
- [ ] Does the method avoid closing caller-owned resources?
- [ ] Are transaction boundaries explicit?
- [ ] Does the service layer own business transactions when multiple repository calls must succeed together?

## Logging and Exceptions

- [ ] Is logging done through the facade?
- [ ] Is useful context included?
- [ ] Are duplicate stack traces avoided?
- [ ] Does each catch block explain why the exception is handled?
- [ ] Are business errors represented by custom checked exceptions where appropriate?

## Maintainability

- [ ] Is the code clear to a developer unfamiliar with the project?
- [ ] Is the class layout predictable?
- [ ] Are names specific enough to reveal intent?
- [ ] Are package boundaries cohesive?
- [ ] Is the package size a design signal if it exceeds roughly 10-12 classes?
- [ ] Is the code explicit rather than clever?

## Performance

- [ ] Are obvious performance risks considered?
- [ ] Is database work pushed to the database where practical?
- [ ] Is caching justified by a clear access pattern or evidence?
- [ ] Are low-level optimizations avoided unless justified?

## Style

- [ ] Does new Java follow the Java Coding Standard?
- [ ] Are braces always present?
- [ ] Is there one return statement per method?
- [ ] Are annotations minimal and alphabetized?
- [ ] Are streams and lambdas used only when clearer than loops?
- [ ] Are classic switch statements used by default?

## Review Outcome

- [ ] Required fixes are clearly distinguished from suggestions.
- [ ] Nitpicks are marked as optional unless they violate a team standard.
- [ ] Larger refactoring opportunities are tracked separately unless they block the change.
- [ ] The pull request explains any deliberate deviation from the handbook.
