# Software Design Philosophy

> Software should read like a well-written book. Beautiful code is like beautiful poetry: its meaning is clear, its structure is deliberate, and its purpose is immediately understood.

## Purpose

This document captures the design values that guide the rest of the handbook. It is intentionally language-independent. The Java standard is one expression of these principles, but the same philosophy can guide shell scripts, Python, JavaScript, YAML, Terraform, and future languages or tools.

## Core Philosophy

Software should be written for people first and computers second. The compiler only needs the program to be correct; future developers need it to be understandable.

Therefore, code should be explicit, predictable, cohesive, testable, and easy to reason about. Abstractions, frameworks, language features, and design patterns are valuable only when they reduce long-term maintenance cost and cognitive load.

## Principles

### Minimize Cognitive Load

Good software minimizes the cognitive effort required to understand, debug, test, and maintain it. Assume the future maintainer is intelligent but unfamiliar with the code. That maintainer may be a junior developer, a senior engineer who joined recently, the original author six months later, or someone debugging an incident under pressure.

### Prefer Explicit Behaviour

Important behaviour should be visible in the code. Hidden dependency injection, global lookups, implicit framework magic, compressed lambdas, and unclear control flow make software harder to reason about.

Explicit code is not the same as verbose code. The goal is not more lines. The goal is less guessing.

### Optimize for Human Comprehension Before Conciseness

Short code is not automatically better code. Prefer straightforward code that a competent developer can read quickly over compact code that requires mental expansion.

Conciseness is valuable only after clarity has been preserved.

### Prefer Principles Over Products

Frameworks, libraries, and tools evolve. Sound engineering principles endure. A logging framework, mocking library, test runner, or AI assistant can be replaced. The principles behind decoupling, testability, explicit dependencies, and maintainable design should remain stable.

### Abstractions Carry Cost

Every abstraction carries a maintenance cost. Introduce abstractions only when the engineering benefit exceeds that cost.

Patterns, interfaces, factories, builders, facades, and strategies are tools. They should solve real problems, not demonstrate knowledge of terminology.

### Construction Should Reflect Validity

If an object cannot exist without a value, require that value during construction. If a value is optional, allow it to be supplied through a clear optional path. Validate before exposing a completed object.

### Fail Fast

Detect invalid state as early as practical. Required dependencies should be validated during construction, not discovered several layers later when a method happens to use them.

Defensive checks still have value, but they do not replace early validation.

### Make Important Characteristics Deliberate

Important software characteristics should be explicit design decisions, not accidental side effects of an implementation.

This applies to:

- thread safety,
- immutability,
- transaction ownership,
- resource ownership,
- public APIs,
- exception contracts,
- extension points,
- and lifecycle management.

### Design for Testability

Tests are the first consumer of a design. If production code is difficult to unit test, that often indicates a design problem: hidden dependencies, mixed responsibilities, static state, tight coupling, or business logic tangled with I/O.

Testability is not only a testing concern. It is a design discipline.

### Maintainability Is a Primary Requirement

Most professional software work happens after the first version is written. Code should be built for long-term maintenance, not only initial delivery.

Self-documenting, well-structured code reduces the cost of future changes and the risk of accidental breakage.

### Business Reality Matters

Engineering quality matters, but scope must be visible. Improve code where practical, especially when working in risky or untested areas, but do not allow a small task to silently become a broad rewrite.

When the foundation is quicksand, broad refactoring may be necessary. When it is not necessary, keep changes focused.

## Summary

The handbook favours clarity over cleverness, maintainability over fashion, explicit design over accidental behaviour, and principles over products.
