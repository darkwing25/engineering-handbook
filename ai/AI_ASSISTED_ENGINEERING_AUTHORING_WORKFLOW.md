# AI-Assisted Engineering Authoring Workflow

This workflow defines how AI should help maintain this handbook. AI amplifies engineering judgment; it does not replace it.

## 1. Discovery

Use interviews and existing code to identify principles, recurring decisions, trade-offs, and terminology. During discovery, AI may ask questions and point out patterns, but it must not invent philosophy.  Capture rationale, not just decisions.  Distinguish personal preferences from engineering principles.

## 2. Architecture

Organize the material into durable document boundaries:

- Software Design Philosophy
- Architecture and Development Practices
- Java Coding Standard
- Code Review Checklist
- Engineering Decision Records
- AI instructions
- Examples

The architecture phase decides where ideas belong and how documents reference each other.

## 3. Authoring

Draft finished prose, examples, and EDRs from established decisions. Write like a professional engineering handbook, not a random list of rules.

Authoring must:

- preserve the engineer's intent,
- explain rationale,
- use consistent terminology,
- avoid stubs,
- include examples where useful,
- and keep the philosophy as the source of truth.

## 4. Technical Review

Review for:

- internal contradictions,
- unclear reasoning,
- missing examples,
- broken links,
- drift between standards and EDRs,
- and accidental modernization.

Technical review may improve clarity, but it must not change the underlying engineering decision without calling that out explicitly.

## 5. Engineering Review

The engineer has final authority over philosophy. If an AI suggestion would alter intent, it must be presented as a proposed change, not silently applied.  Resolve open questions.

## 6. Publication

Before publication:

- confirm all documents are cross-linked,
- ensure each EDR is one topic,
- verify examples match the standard,
- run available tests,
- and mark unresolved questions clearly.

Published guidance should be stable. Change it deliberately through EDRs rather than casual edits.
