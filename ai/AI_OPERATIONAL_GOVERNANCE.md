# AI Operational Governance

AI can substantially improve engineering work, but it does not own engineering intent or accountability. Asimov's laws of robotics are acknowledged as ethical inspiration; operational policy is defined through concrete, auditable controls.

## Ethical Inspiration, Not Specification

Isaac Asimov's Three Laws of Robotics, first stated together in the 1942 story *Runaround*, and the later Zeroth Law in *Robots and Empire* inspire an emphasis on human safety, responsible obedience, and preservation. They are fictional thought experiments rather than an executable engineering specification.

Terms such as harm, human, humanity, action, and inaction are context-dependent. The laws do not define who may authorize an action, how competing people and harms are weighed, how privacy and property rights apply, how uncertainty is measured, or how a system is audited and stopped. The Zeroth Law can also place an AI's estimate of collective benefit above an identifiable person's safety or rights.

This handbook therefore implements the ethical intent through explicit authorization, risk classification, data boundaries, audit records, reversibility, shutdown, and human escalation.

Bibliographic note: Isaac Asimov, “Runaround,” *Astounding Science Fiction* (March 1942); Isaac Asimov, *Robots and Empire* (Doubleday, 1985).

## Human Accountability

- Hold AI-generated work to the same design, testing, security, documentation, and review standards as human-written work.
- A named human must understand and approve accepted work.
- A sole developer may self-review. When another qualified reviewer is available, use an independent human reviewer.
- Plausible-looking output is not evidence of correctness.

## Approved Services and Data

- Use only organization-approved AI services for project work.
- Apply data-classification rules before sharing source, logs, customer data, algorithms, infrastructure details, or internal documentation.
- Never include secrets in prompts or tool inputs.
- Use default denial and least privilege for AI access to tools and data.
- Govern prompt and output audit records through access controls and retention policies.

## Three-Tier Risk Model

Evaluate risk as the combination of potential impact, reversibility, exposure, and uncertainty. Classify the action, not the model's brand or tier. A less capable or less familiar model may increase uncertainty and therefore raise risk; it never removes a required control. A human may always raise a risk classification. AI must not downgrade an action that policy defines as high risk.

### Low Risk

Low-risk work is scoped, local, reversible, and does not expose sensitive information. Examples include repository inspection, reading known non-sensitive files, searching code, running established tests, and making explicitly requested local edits.

Use a lightweight plan or proceed within the clearly authorized task.

### Moderate Risk

Moderate-risk work includes broad refactoring, dependency or build changes, unfamiliar scripts, local commits, and non-production changes that affect persisted data.

Write a plan, identify verification and rollback, and ask about material ambiguity. Independent AI critique is optional.

### High Risk

High-risk work includes destructive actions, production deployment, database migration, permission or secret changes, access to sensitive data, purchases, external communications, pushing or merging code, public publication, costly operations, and material scope expansion.

High-risk work requires:

1. a written plan,
2. independent AI critique,
3. explicit human approval,
4. approval gates at consequential stages,
5. least-privileged and sandboxed execution where possible,
6. audit records,
7. and a tested recovery or rollback path.

Uncertain, destructive, reputationally sensitive, or out-of-scope actions remain human decisions.

An independent AI critique is a separate review pass that did not author the plan and is explicitly tasked with finding unsafe assumptions, missing boundaries, escalation paths, and rollback failures. A different model is preferable when available, but separation of role and context matters more than model branding. The critique informs the human decision; it does not approve the plan.

## Agentic Task Plan

Before autonomous execution, define:

- objective and success criteria,
- permitted tools and data,
- resource and token limits,
- scope and authorization boundaries,
- known unproductive paths,
- stop conditions,
- checkpoints for human inspection,
- and recovery or rollback.

The AI asks focused questions when the plan is inconsistent or an ambiguity changes risk or scope.

## Automated Response

AI or automated remediation is limited to preapproved, auditable, bounded, and preferably reversible runbooks. Informational events, warnings, and actionable alerts may be handled by humans or approved automation. Unsafe or unresolved conditions escalate to a human owner.

## Commit and Pull-Request Disclosure

AI may draft commit and pull-request descriptions. The accountable human verifies and edits them for accuracy and confirms the change's intent, risks, and test evidence.

AI-assisted commits include trailers such as:

```text
AI-Assisted: yes
Human-Reviewed-by: Full Name <name@example.com>
```

The reviewer name is supplied as an explicit human attestation. On a sole-developer project it may be a self-review; a team uses an independent qualified reviewer when available.

Every pull request includes these fields, using `no` and `not applicable` where no AI assisted the change:

```text
AI Assistance: yes | no
AI Contribution: code, tests, documentation, review, description, or not applicable
Human Reviewer: Full Name
```

Signed provenance should be used when supported. A plain checksum may prove message integrity, but it does not prove AI authorship without a trusted signed attestation.

## Incident Learning

Preserve enough audit information to determine what an AI was authorized to do, what it attempted, what tools and data it used, what approvals occurred, and how the system responded. Feed incident lessons into controls, project documentation, and this handbook.
