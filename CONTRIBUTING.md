# Contributing

Corrections and thoughtful proposals are welcome. The goal is to make this handbook easier to understand, reuse, adapt, and maintain without changing its engineering direction by accident.

## Good Contributions

Useful contributions include:

- factual corrections,
- broken link fixes,
- unclear wording that can be made more precise,
- examples that better demonstrate an existing standard,
- substantial proposals that explain the trade-offs involved,
- and questions that expose ambiguity in the current guidance.

## Editorial Direction

Maintainers retain editorial direction for the handbook. A contribution may be declined even when it is technically reasonable if it changes the handbook's voice, scope, or engineering philosophy.

Please distinguish corrections from proposals. Corrections should identify what is inaccurate or inconsistent. Proposals should explain the problem being solved, the recommended change, alternatives considered, and effects on standards, EDRs, examples, lifecycle practices, and AI guidance.

## Change Workflow

- Use a short-lived branch rather than committing directly to `main`.
- Use a pull request when a CI-backed remote is available; a local-only repository may merge a tested branch locally.
- Update documentation, examples, runbooks, and affected EDRs in the same change.
- Run `mvn clean verify` when Java examples or their governing standards change.
- Run `git diff --check`, verify links, and inspect the complete change before merge.
- Explain deliberate deviations, risks, and any unavailable check.

A sole maintainer may self-review. Another qualified human reviewer should participate when available.

## AI Assistance

AI-assisted contributions follow [AI Operational Governance](ai/AI_OPERATIONAL_GOVERNANCE.md). The accountable human must understand and validate the change, its tests, and its description.

AI-assisted commits include:

```text
AI-Assisted: yes
Human-Reviewed-by: Full Name <name@example.com>
```

Every pull request includes the `AI Assistance`, `AI Contribution`, and `Human Reviewer` fields defined by the governance document. Never provide secrets or unapproved protected information to an AI service.

## Licensing

By contributing prose or documentation, you agree that it will be made available under the Creative Commons Attribution 4.0 International License in `LICENSE`.

By contributing code under `examples/`, you agree that it will be made available under the MIT License in `examples/LICENSE`.
