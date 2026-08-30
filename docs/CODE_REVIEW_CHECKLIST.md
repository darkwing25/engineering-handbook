# Code Review Checklist

Use this checklist before a pull request or local branch merge and during review. A sole developer may self-review; another qualified reviewer should participate when available. Explain the reason for material feedback.

## Intent and Correctness

- [ ] The change solves the approved problem without hidden scope expansion.
- [ ] Edge cases, invalid inputs, absence, and failure paths are deliberate.
- [ ] Exceptions are specific, meaningful, and preserve causes.
- [ ] Public contracts and compatibility consequences are understood.

## Comprehensibility and Design

- [ ] A developer new to the code can identify each object's responsibility and collaborators.
- [ ] Classes, methods, branches, loops, nesting, packages, and parameter counts have been reviewed as design signals.
- [ ] The design uses real object-oriented decomposition rather than global state or procedural code collected in a class.
- [ ] Inheritance represents substitutability; collaboration is used for ordinary reuse.
- [ ] Abstractions and patterns solve present engineering needs.
- [ ] Adapters translate, facades simplify, and business decisions remain in business objects.
- [ ] Large selection logic becomes strategies when behaviour genuinely varies.

## Dependencies and Testability

- [ ] Required collaborators are explicitly constructor-injected and validated.
- [ ] Business methods do not construct hidden side-effecting collaborators.
- [ ] Class-owned defaults are safely lazy-initialized and narrowly replaceable.
- [ ] Injectable factories or resolvers are used when orchestration selects a collaborator per call.
- [ ] Package-private helper tests supplement rather than replace public-method tests.
- [ ] Reflection-based private testing is confined to justified legacy code.
- [ ] Every application-visible method has a behavioural unit test, including constructors, accessors, and generated APIs.
- [ ] Every unit-test method has Javadoc identifying its happy path, edge condition, or failure trigger.
- [ ] Test classes and methods use the narrowest JUnit-supported visibility, and test attributes remain private unless an extension requires otherwise.
- [ ] Tests are deterministic, isolated, and organized with Arrange, Act, Assert.
- [ ] Failure paths and critical rules are meaningfully asserted rather than merely executed for coverage.
- [ ] Line and branch coverage satisfy the greenfield threshold or brownfield non-regression policy.

## Resources, Transactions, and Data

- [ ] Resource ownership and cleanup are explicit; caller-owned resources remain open.
- [ ] `try-with-resources` is used where appropriate.
- [ ] The layer that understands the business operation owns its transaction.
- [ ] Schema and data-format changes preserve backward compatibility through the rollback window.
- [ ] Feature flags identify owner, purpose, default, rollback, and removal condition.

## Logging, Exceptions, and Observability

- [ ] Logging uses the application facade and excludes secrets.
- [ ] A failure is logged once at the boundary that handles or reports it.
- [ ] Checked and unchecked exceptions match recoverability and caller expectations.
- [ ] Catch-block reasoning is documented when it is not self-evident.
- [ ] Deployable behaviour updates logs, health checks, metrics, events, warnings, alerts, dashboards, and runbooks as needed.
- [ ] Alerts require meaningful response; obsolete or noisy signals are removed.

## Security and Dependencies

- [ ] Data classification, trust boundaries, authorization, and abuse cases are addressed proportionately.
- [ ] Secrets remain outside source, logs, artifacts, and AI prompts.
- [ ] Dependencies and plugins are necessary, explicitly versioned, and vulnerability-reviewed.
- [ ] Deferred upgrades or security work record risk, owner, and review condition.

## Delivery and Recovery

- [ ] Pull-request or merge gates include a clean build, unit tests, and applicable integration tests.
- [ ] The same immutable artifact is promoted through environments.
- [ ] Configuration is external, validated at startup, and represented by versioned non-secret schemas or examples.
- [ ] Rollback and roll-forward are documented and tested proportionately.
- [ ] Irreversible actions are identified and approved before deployment.
- [ ] Backup and restoration expectations are tested when durable data is owned.

## Java Style

- [ ] Allman braces, tabs, mandatory braces, names, and Javadoc follow the standard.
- [ ] Every Java type contains the complete section-banner layout.
- [ ] Every method has exactly one return statement at its end, or one natural exit when `void`.
- [ ] A local variable is not introduced solely to return it on the next line.
- [ ] Constructor-invoked setters are non-overridable.
- [ ] Declarations and expressions are not wrapped unnecessarily when they fit the preferred line length.
- [ ] Functional-interface operations are explicit when a lambda would hide behaviour or create cognitive friction.
- [ ] Streams, switch expressions, and annotations are used only when they improve clarity.

## Documentation, Decisions, and AI

- [ ] Documentation, examples, runbooks, and affected EDRs change with the behaviour they describe.
- [ ] Project and language engineering documents record versions, tools, risks, owners, and deviations.
- [ ] AI-generated work meets the same engineering standard as human work.
- [ ] A named human understands and accepts the change.
- [ ] Approved AI services and data-handling rules were followed.
- [ ] Required AI commit trailers and pull-request disclosure are present.
- [ ] Moderate- or high-risk agentic work followed the required plan, critique, approval, and recovery controls.

## Review Outcome

- [ ] Required fixes are distinguished from suggestions.
- [ ] Optional preferences are identified as such.
- [ ] Strategic refactoring is separately approved unless it blocks safe delivery.
- [ ] Deliberate deviations state their reason, risk, owner, and review condition.
