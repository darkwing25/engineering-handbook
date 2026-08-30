# Software Design Philosophy

> Software is written for people first and computers second. Well-written software should read like a well-written book. Beautiful code is like beautiful poetry: its meaning is clear, its structure is deliberate, and its purpose is immediately understood.

## Purpose

This document is the language-independent foundation of the handbook. It applies to application code, scripts, infrastructure, configuration, tests, build definitions, operational automation, and documentation.

Language standards express these principles in forms appropriate to their languages. Java may favour object-oriented design; shell, PowerShell, Ansible, HTML, CSS, JavaScript, Python, Perl, and future languages may require different structures. A language-specific standard must explain those differences rather than imitate Java where the language does not support the same model well.

## Human Comprehension Is the Organizing Principle

The compiler needs a program to be correct. Developers must also be able to understand what the program does, how its parts collaborate, why decisions were made, and how to change it safely.

Code should minimize cognitive load so a developer can concentrate on behaviour, business intent, and architecture instead of decoding clever syntax, hidden dependencies, inconsistent structure, or unnecessary abstraction. This applies equally to junior developers, experienced engineers new to the system, the original author returning later, and responders working under incident pressure.

Correctness is mandatory. Readability, maintainability, debuggability, testability, security, and operability are also primary requirements, not optional polish.

## Principles

### Prefer Explicit Behaviour

Important behaviour, dependencies, ownership, failure modes, and lifecycle decisions should be visible. Explicit code is not the same as verbose code: the goal is less guessing, not more lines.

### Optimize for Comprehension Before Conciseness

Short code is not automatically clear code. Prefer straightforward control flow and precise names over compressed expressions that require mental expansion.

### Prefer Principles Over Products

Tools, frameworks, vendors, and AI models change. Durable principles should survive those changes. Tool-specific implementation belongs in project or language documentation.

### Respect the Cost of Abstraction

Every abstraction must be learned, tested, operated, and maintained. Interfaces, factories, builders, facades, strategies, inheritance, functional composition, and generated code are justified only when their benefit exceeds that cost.

### Make Valid State Easy to Construct

Required values and dependencies have no valid default. They must be supplied and validated before use. A dependency may be initialized lazily only when its owner provides a safe default. Missing, malformed, or internally inconsistent configuration should fail fast with a diagnostic that helps the operator correct it.

### Design for Testability

Tests are the first consumer of a design. Hidden collaborators, static construction in business logic, mixed responsibilities, global state, and tangled I/O make both the code and its behaviour harder to understand.

Every application-visible method requires behavioural coverage, including constructors, accessors, and generated APIs. Compiler-created synthetic internals outside the observable application contract are not direct test targets.

### Make Ownership Explicit

Resource, transaction, data, configuration, security, operational, and decision ownership must be clear. A system that works only because one person remembers how it works is not maintainable.

### Design for Change and Recovery

Software will change, dependencies will age, environments will drift, incidents will occur, and people will leave. Build reproducibly, document locally, preserve rollback paths, test restoration, and record decisions so change does not depend on tribal knowledge.

### Prefer Reversible Progress

Small, observable, reversible changes reduce risk. Irreversible changes require explicit identification and approval. Refactoring should keep scope visible; deployments should promote a tested immutable artifact; data changes should preserve compatibility through the rollback window.

### Treat Security and Privacy as Design Properties

Least privilege, default denial, data classification, secret protection, dependency monitoring, auditability, and human accountability are part of system design. They are not tasks deferred until release.

### Keep Scope Visible

Improve unsafe or untestable foundations when required, but do not allow a small task to become a broad rewrite without making that expansion visible. Required, adjacent, and strategic refactoring have different approval needs.

### Use Evidence for Optimization

Correctness and clarity precede low-level optimization. Address credible risks and measured bottlenecks. Do not trade comprehensibility for speculative performance claims.

## Principles and Conventions

A principle explains an enduring engineering value. A convention provides consistency within a language or project. Deliberate conventions remain mandatory where adopted even when another convention could also be reasonable.

When a language or project must deviate, document the reason locally. Feed reusable lessons back into the central handbook; keep genuinely local constraints in the project.

## Summary

The handbook favours human comprehension over cleverness, explicit design over accidental behaviour, evidence over fashion, reversible change over surprise, and durable knowledge over tribal memory.
