# Project Engineering

This document records how the Engineering Handbook repository implements the language-independent standards in this repository. Read the root [Engineering entry point](../ENGINEERING.md) first.

## Ownership and Scope

This is an independently maintained documentation and example-code project. The repository maintainer is the project owner and escalation contact. It does not currently have a separate organizational team, backup contact, management chain, or project code. If organizational ownership is introduced, this section must name the durable owning team, primary and backup technical contacts, management escalation, and project code.

The repository contains handbook prose, AI engineering guidance, engineering decision records, and independently buildable Java examples. It does not operate a deployed service or own production data.

## Source Control and Review

Work is performed on short-lived branches. Changes reach `main` through a pull request when the repository is hosted with CI. A purely local clone may merge a tested branch locally. Direct commits to `main` are not the normal workflow.

The sole maintainer may perform the required human review. Another qualified reviewer should review changes when one is available. Pull-request and merge boundaries must pass the documented checks; an intermediate commit is not required to pass every check.

## Build and Test

The aggregate Java build runs from the repository root:

```shell
mvn clean verify
```

Each example is independently selectable from the repository root:

```shell
mvn -pl :logging-example clean verify
mvn -pl :customer-example -am clean verify
mvn -pl :testing-example clean verify
```

The build compiles the selected example, runs unit tests, and enforces the coverage rules documented in [Java Engineering](JAVA_ENGINEERING.md). The customer example uses `-am` to build its logging-facade dependency in the same reactor.

Documentation-only changes additionally require:

```shell
git diff --check
```

Reviewers must verify that Markdown links resolve and that examples and EDRs agree with the normative documents they support.

## Continuous Integration and Repository Health

A CI-backed host should run the aggregate clean build at pull-request and merge boundaries. A scheduled health build should run at least weekly against the pinned dependency set so build-tool, Java, runner, and CI drift is detected even when the handbook is inactive.

The repository should add Markdown linting, static analysis, secret scanning, and dependency vulnerability scanning to both the contributor workflow and CI. Until those checks are automated, reviewers perform the corresponding manual checks and record any material limitation in the change.

Scheduled-build and vulnerability-monitoring notifications go to the repository maintainer. If the repository becomes organizationally owned, alerts must also reach a backup contact or durable team queue.

## Dependencies and Vulnerabilities

Java, Maven plugin, and test-library versions are explicit in the Maven build. Routine dependency updates are planned and tested. Urgent vulnerabilities are handled independently of the routine update schedule. An intentionally deferred update must record its reason, risk, owner, and review condition.

## Artifacts, Environments, and Configuration

The repository does not deploy a runtime application. Its release artifacts are the reviewed handbook source and the compiled example artifacts produced from that exact revision. No environment-specific rebuild is required.

The examples require no secrets or environment-specific configuration. Future examples that introduce configuration must keep non-secret schemas, defaults, and templates in the repository, inject actual environment values externally, and validate them at startup.

## Security, Privacy, and AI

Do not add secrets, customer data, proprietary third-party material, or unapproved internal information. AI-assisted work follows [AI Operational Governance](../ai/AI_OPERATIONAL_GOVERNANCE.md). Only approved AI services may receive repository content, and AI-assisted commits and pull requests use the required disclosures.

The repository's principal trust boundary is between public handbook material and any private source material used while authoring it. Contributors must confirm that submitted content may be published.

## Operations, Recovery, and Archival

The project has no production database, service alerts, recovery-point objective, or recovery-time objective. Git history and the repository host provide source recovery. Releases should be tagged or otherwise tied to immutable commits so a prior handbook revision can be restored.

If archived, the final revision must state why, identify the responsible maintainer, preserve the last known successful build versions and commands, record external publication locations, and explain how to restore and republish the handbook.

## External Systems

The repository may use a Git host and CI service. Access is granted through the owner's normal account-management process; credentials never appear in this repository. Any future publication, package, monitoring, or documentation system must be listed here with its purpose, owner, and access-request procedure.

## Approved Deviations and Known Risks

- Human review may be self-review because this is a sole-maintainer project.
- A local-only clone may use a tested local branch merge instead of a pull request.
- The absence of automated Markdown, static-analysis, secret-scanning, and vulnerability-scanning gates is a known maturity gap to be closed as repository automation is added.

Review this document whenever ownership, hosting, CI, publication, build tooling, or repository risk changes.
