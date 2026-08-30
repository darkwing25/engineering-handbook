# Engineering Lifecycle Practices

This document defines language-independent practices for building, delivering, operating, and maintaining software. Projects document their concrete implementation in the files required by the [Project Documentation Standard](PROJECT_DOCUMENTATION_STANDARD.md).

`Must` identifies a requirement. `Should` identifies an expected practice that may be varied with documented rationale. `May` identifies an option.

## Source Control and Review

- Use short-lived branches and integrate frequently into a protected main branch.
- Long-lived development or release branches require a documented project need.
- Do not commit directly to main by default, including on sole-developer projects.
- A local-only repository may merge a tested branch locally because a pull request is unavailable.
- When a CI-backed remote exists, changes must reach main through a pull or merge request, including sole-developer changes.
- Human review is required when another qualified reviewer is available. A sole developer may self-review.
- Individual commits are encouraged to be coherent and buildable, but the enforceable quality gates apply to the complete pull request and merge boundary.

## Build and Continuous Integration

Every build must be reproducible from documented commands and pinned inputs.

At pull-request and merge boundaries, CI must perform:

- a clean build,
- unit tests,
- and applicable integration tests.

Projects should also run static analysis, formatting checks, linters, dependency vulnerability scanning, and secret scanning. These checks should run in the developer's IDE or local workflow first and in CI as enforcement. A missing maturity check is documented with its risk and adoption plan.

Maintained projects should run scheduled health builds against their pinned dependency set, preferably daily or weekly. These builds detect JDK, build-tool, runner, and CI-platform drift even when application code is unchanged.

## Test Quality and Coverage

- Greenfield projects must enforce at least 80% line and 80% branch coverage in CI.
- Coverage exclusions require explicit rationale and approval.
- Brownfield projects record an honest baseline, prevent regression, and improve coverage deliberately.
- Coverage measures execution, not assertion quality. Critical behaviour, failure paths, security boundaries, and business rules require meaningful tests regardless of percentages.
- Generated tests receive the same review as handwritten tests. Repeating implementation logic is not independent verification.

Language-specific standards define the detailed test style.

## Dependencies

- Pin dependency and plugin versions. Do not use floating versions such as `latest`.
- Keep dependencies minimal and justified.
- Monitor vulnerabilities daily when automation permits.
- Schedule routine upgrades so compatibility work is planned rather than surprising a delivery team.
- Treat urgent vulnerabilities outside the routine schedule.
- Document intentionally deferred upgrades, their risks, owner, and review condition.

## Configuration and Secrets

- Keep non-secret schemas, defaults, validation rules, and example templates with the source.
- Keep actual environment values external to the artifact.
- Validate required configuration at startup and fail fast with clear diagnostics.
- Never commit or prompt an AI with passwords, credentials, tokens, private keys, or other secrets.
- Obtain secrets from an approved external store or runtime injection mechanism.

## Artifact Promotion and Environments

Build one immutable artifact and promote that exact artifact through environments. Do not rebuild source separately for test, staging, and production.

Environment-specific configuration may change behaviour, but the code promoted to production must be the code tested in lower environments.

## Releases, Rollback, and Data Evolution

- Production releases should have documented and tested rollback and roll-forward procedures.
- Irreversible changes must be identified and approved before deployment.
- Immutable packaging, including containers where appropriate, should make restoration to a known version reliable.
- Database schemas and persisted formats remain compatible with the preceding application version throughout the rollback window.
- Use an expand-migrate-contract sequence for destructive schema or format changes.
- Feature-flagged data changes preserve the old format while rollback remains possible.

Every feature flag documents its owner, purpose, safe default, rollback behaviour, and removal condition.

## Versioned Contracts

Public APIs, persisted formats, events, and configuration contracts are versioned interfaces. Breaking changes require explicit compatibility, deprecation, and migration plans.

## Observability

Every deployable service should define observability proportional to its risk and architecture:

- diagnostic logs,
- health and readiness checks,
- key metrics,
- correlation or request identifiers,
- informational events,
- warning notifications,
- actionable alerts,
- dashboards,
- and response runbooks.

Informational events record useful history without requiring action. Warning notifications identify conditions that may need monitoring. Actionable alerts require investigation or remediation by an accountable human or approved automation.

Review signals regularly and remove obsolete or noisy conditions. If an observability capability is impractical, document the limitation and compensating approach.

## Security and Privacy

- Use default denial and least privilege for people, services, automation, and AI.
- Document sensitive data, trust boundaries, authentication, authorization, and significant abuse or failure scenarios at a depth proportionate to risk.
- Use organization-approved services for protected data and code.
- Make authorization, auditability, shutdown, recovery, and escalation explicit.
- Treat customer privacy, proprietary algorithms, infrastructure details, and internal documentation according to their data classification.

## Backups and Recovery

Projects that own durable data document:

- backup scope and frequency,
- restoration steps,
- recovery-point and recovery-time expectations,
- owners and access requirements,
- and recurring restoration tests.

A backup that has not been restored in a representative test is an unverified assumption.

## Ownership and Archival

Every project identifies an owner. Company projects should name a durable owning team, primary and backup technical contacts, management escalation, and the relevant product or project code. Sole-developer projects document the owner that actually exists.

Archived projects retain ownership and as much repository-local recovery documentation as practical, including:

- the reason for archival,
- the last known build and deployment process,
- dependencies and infrastructure,
- data and third-party integrations,
- access procedures,
- decisions and known risks,
- and restoration instructions.

External systems may supplement repository documentation. The repository still records what the external material contains, where it lives, who owns it, and how access is requested.

## Incident Learning

Significant production incidents require a blameless post-incident review covering:

- impact,
- contributing conditions,
- detection and response gaps,
- corrective actions and owners,
- and lessons for project documentation or the central handbook.

The purpose is to improve systems and decisions, not assign personal blame.

## Documentation as Implementation

Documentation, examples, runbooks, and affected decision records change in the same pull request as the behaviour they describe. Documentation drift is a defect.

## AI-Assisted Engineering

AI-generated work follows [AI Operational Governance](../ai/AI_OPERATIONAL_GOVERNANCE.md), receives the same engineering scrutiny as human-written work, and remains under accountable human approval.
