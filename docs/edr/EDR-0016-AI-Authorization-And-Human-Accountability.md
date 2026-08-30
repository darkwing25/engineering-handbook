# EDR-0016: AI Authorization and Human Accountability

## Decision

AI operates through default denial, least privilege, explicit risk classification, auditable authorization, bounded tools and data, stop conditions, and recovery controls. A named human understands and remains accountable for accepted output.

High-risk work requires a written plan, independent AI critique, explicit human approval, staged approval gates, and a tested rollback path. Independent critique is optional for moderate-risk work.

## Rationale

AI can produce valuable work and can also pursue an objective through an unexpected path. Concrete controls are enforceable in ways that general assurances are not. Asimov's laws are ethical inspiration, not an operational specification.

AI-generated code receives the same design, test, security, documentation, and review scrutiny as human-written code. Commit trailers and pull-request fields disclose assistance and name the human reviewer.

## Trade-Offs

Planning and approvals add friction, especially for high-impact work. The cost is proportional to the consequences of mistaken, destructive, costly, sensitive, or externally visible action.

## Alternatives Considered

- Grant broad autonomous access.
- Treat plausible output as reviewed.
- Use a checksum alone as proof of AI authorship.

These do not establish authorization, understanding, or trusted provenance.

## Related Guidance

- `../../ai/AI_OPERATIONAL_GOVERNANCE.md`
