# EDR-0017: Reproducible Dependencies and Health Builds

## Decision

Pin dependencies and build plugins to explicit versions. Run scheduled health builds against the pinned set, preferably daily or weekly, even when application code has not changed.

Monitor vulnerabilities daily when automation permits. Handle urgent vulnerabilities independently from planned routine upgrades, and document intentionally deferred updates.

## Rationale

Floating versions can introduce an unplanned breaking change into an old build. Pinned versions make a revision reproducible, while scheduled builds reveal external drift in the JDK, build tool, runner image, repository, or CI platform before a dormant project must be restored urgently.

## Trade-Offs

Pinned versions require planned maintenance, and scheduled builds consume resources. That predictable work is preferable to a surprise rebuild or emergency migration.

## Alternatives Considered

- Use `latest` dependency versions.
- Build inactive projects only when a new change is required.

Both defer compatibility discovery until the cost and urgency are highest.
