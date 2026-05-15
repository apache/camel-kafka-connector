# Security Policy

## Supported Versions

Camel Kafka Connector tracks the Apache Camel release train. To see which
versions are supported please refer to the
[download page](https://camel.apache.org/download/) and the compatibility
matrix in the [project documentation](https://camel.apache.org/camel-kafka-connector/).

## Reporting a Vulnerability

Camel Kafka Connector is an Apache Camel sub-project. For information on how to
report a new security problem please see [here](https://camel.apache.org/security/).
Do not open a public GitHub issue or pull request for an unpublished
vulnerability — follow the private Apache process and stop.

## Security Model

Before submitting a report, please read the project's
[Security Model](docs/modules/ROOT/pages/security-model.adoc). It documents who
is trusted, where the trust boundaries sit, which behaviours count as a Camel
Kafka Connector vulnerability, and which categories are out of scope:

- A connector configuration is trusted, operator-authored route code — code
  execution by whoever can submit one (arbitrary `camel.sink.url` /
  `camel.source.url`, `#class:` beans, SMT class options) is by design.
- Authentication, exposure and secret storage of the Kafka Connect REST API
  and config topic are the Kafka Connect runtime's responsibility.
- Vulnerabilities in the wrapped Camel component, data format, expression
  language or Kamelet are governed by the
  [Apache Camel Security Model](https://camel.apache.org/manual/security-model.html)
  and triaged by the Camel PMC against `apache/camel` or the Kamelets catalog.
- DoS through unthrottled routes and third-party transitive CVEs not reachable
  through Camel Kafka Connector code are out of scope.

Reports outside the documented scope will be closed with a reference to that
page; reports whose root cause is a wrapped Camel component or Kamelet will be
redirected to the Apache Camel security process.
