# Java Engineering

This document records the Java implementation choices for the Engineering Handbook examples. It supplements the [Java Architecture and Development Practices](JAVA_ARCHITECTURE_AND_DEVELOPMENT_PRACTICES.md) and [Java Coding Standard](JAVA_CODING_STANDARD.md).

## Runtime and Build

- Java release: 25
- Build system: Maven 3.9 or later
- Aggregate build: `mvn clean verify` from the repository root
- Individual build: `mvn -pl :<example-artifact> -am clean verify` from the repository root

Java 25 remains the example baseline until a later long-term-support release is deliberately evaluated and adopted. The central handbook itself remains version-independent.

All dependency and plugin versions are pinned by the root Maven build. The example modules inherit those versions while remaining independently invokable.

## Testing and Coverage

The examples use JUnit 6.0.3, the latest stable JUnit release deliberately adopted by the repository, and Mockito 5.23.0 where a collaborator must be substituted. Test classes, test methods, and lifecycle methods are package-private because JUnit does not require public visibility and recommends omitting it unless a technical constraint requires otherwise. Test attributes remain private unless an extension specifically requires broader visibility.

The build pins Maven Compiler Plugin 3.15.0, Surefire 3.5.5, Enforcer 3.6.3, JaCoCo 0.8.15, Clean Plugin 3.5.0, Resources Plugin 3.5.0, and JAR Plugin 3.5.1. The root `pom.xml` is authoritative when these deliberately adopted versions change.

Tests use Arrange, Act, Assert structure and explicitly cover every application-visible method, including constructors, accessors, and generated APIs. Every test method has Javadoc identifying the exact happy path, edge condition, or failure trigger under test. Package-private production helpers may provide intentional test seams. Reflective invocation of private methods is reserved for legacy code that cannot yet be safely refactored.

The Maven build enforces at least 80% line coverage and 80% branch coverage. Coverage exclusions require an explicit, reviewed rationale. Percentage compliance does not replace behavioural assertions or failure-path testing.

## Organization and Style

Examples use feature-oriented packages. Every Java type uses the complete section-banner layout defined by the coding standard, including interfaces, enumerations, annotations, records, production classes, and test classes.

Source follows Allman braces, tabs with a three-space display width, mandatory braces, and one return statement per method. A direct return expression is preferred when an otherwise trivial method would introduce a local variable solely for the following return. Lambdas are expanded into explicit functional-interface implementations in the examples so the executed behaviour remains visible. Formatting is performed by the contributor's IDE and verified during review until an automated formatter is deliberately selected.

## Architecture

Java examples use object-oriented decomposition: an object has a focused responsibility, owns coherent state or behaviour, and collaborates through explicit interfaces. Business methods do not hide side-effecting collaborators by constructing them internally.

Required collaborators are constructor-injected and validated. A collaborator with a safe class-owned default may be lazily initialized and replaced through a package-private final setter for testing. A public orchestration method may resolve a strategy through an injectable factory and delegate isolated business logic to a package-private helper; tests cover both paths.

Static factories are limited to pure, deterministic construction that does not need test substitution. Recoverable business and persistence failures use checked exceptions and preserve their causes. Exceptions are logged once at the boundary that handles or reports the failure.

## Static Analysis, Generated Code, and Dependencies

The repository has not yet selected an automated Java formatter or static-analysis suite. That gap is recorded in [Project Engineering](PROJECT_ENGINEERING.md); it does not permit reviewers to ignore the coding and architecture standards.

Mechanically generated application code is not exempt from behavioural tests. A generator may reduce typing, but it does not prove that the generated API, mapping, key, or configuration is correct. Compiler-created synthetic internals outside the observable application contract are not direct test targets.

New dependencies require a concrete need, an explicit version, compatible licensing, and review of maintenance and security implications.

## Entry Points

The example modules are teaching libraries rather than executable services and therefore have no `main` method. Their public APIs and tests are their entry points. Each example README explains its design lesson and build command.

## Deviations

There are currently no approved deviations from the Java standards. Any future deviation must be documented here with its reason, risk, owner, and review condition, and a reusable lesson must be proposed for the central handbook.
