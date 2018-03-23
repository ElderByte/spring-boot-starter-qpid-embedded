[ ![Download](https://api.bintray.com/packages/elderbyte/maven/spring-boot-starter-qpid-embedded/images/download.svg) ](https://bintray.com/elderbyte/maven/spring-boot-starter-qpid-embedded/_latestVersion)

# spring-boot-starter-qpid-embedded
Provides spring auto configuration for an embedded AMQP message broker. Based on Apache Qpid.

## Configuration

Configure the embedded qpid broker in your apps `application.yml`
```yaml
# Embedded Qpid Broker
qpid.embedded:
  enabled: true
  port: 5672
  management.port: 8085
  vhost: default
```

When this starter is on the class-path, the broker will automatically get started. 
To disable the broker, set the `qpid.embedded.enabled` property to `false`.
                                                                                                                     enabled`