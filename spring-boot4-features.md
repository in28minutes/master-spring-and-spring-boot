# Spring Boot 4 Features

## 📘 Table of Contents

## 🔗 Quick Navigation
- 🚀 [API Versioning](#1-api-versioning)
- 📡 [OpenTelemetry Implementation](#2-opentelemetry-implementation-in-spring-boot-4)


### 1. API Versioning
API versioning is a feature that helps clients and customers access new functionalities introduced through new API endpoints in an existing application. It ensures backward compatibility, allowing both old and new clients to use the service without disruption.
- Spring Boot 4 has incorporated the `version` attribute within the request mapping annotation.

**_API versioning_** can be configured using either of the following approaches:

- Annotation-based versioning with WebMvcConfigurer **(recommended)**
- Configuration through `application properties`

###  1. Annotation Based using WebMvcConfigurer (Recommended)
```java

package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Select ONE of the following approaches (they cannot be combined)
    // 1. Path Segment Versioning
    // 2. Query Parameter Versioning
    // 3. Request Header Versioning
    // 4. Media Type Versioning (Content Negotiation)

    // 1. Path Segment Versioning
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.usePathSegment(0)
                .addSupportedVersions("1.0","2.0");
    }

    // 2. Query Parameter Based
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useQueryParam("version")
                .addSupportedVersions("1");
    }

    // 3. Request Header Versioning
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useRequestHeader("X-API-VERSION")
                .addSupportedVersions("1.0","2.0");
    }

    // 4. Media Type Versioning (Content Negotiation)
    @Override
    public void configureApiVersioning(ApiVersionConfigurer configurer) {
        configurer.useMediaTypeParameter(MediaType.APPLICATION_JSON, "version")
                .addSupportedVersions("1.0","2.0")
                .setDefaultVersion("1.0")
                .setVersionParser(new CustomApiVersionParser());
    }
}
```

###  2. Properties Based Configuration

```properties
# Basic versioning configuration
spring.mvc.apiversion.supported=1.0,2.0
spring.mvc.apiversion.default=1.0

# Choose ONE versioning strategy:

# Path segment versioning (e.g., /v1/person)
spring.mvc.apiversion.use.path-segment=1

# Query parameter versioning (e.g., ?version=1)
spring.mvc.apiversion.use.query-parameter=version

# Request header versioning (e.g., X-API-VERSION: 1.0)
spring.mvc.apiversion.use.header=X-API-VERSION

# Media type parameter versioning (e.g., Accept: application/json;version=1.0)
spring.mvc.apiversion.use.media-type-parameter[application/json]=version
```

- Add this file `CustomApiVersionParser` for 4. Media Type Conversion(Content Negotiation)

### /src/main/java/com/in28minutes/rest/webservices/restfulwebservices/versioning/CustomApiVersionParser.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.accept.ApiVersionParser;

public class CustomApiVersionParser implements ApiVersionParser {

    @Override
    public Comparable<?> parseVersion(String version) {
        // Handle "v1", "v2" etc.
        if (version.startsWith("v") || version.startsWith("V")) {
            version = version.substring(1);
        }

        // Append ".0" for major versions
        if (!version.contains(".")) {
            version += ".0";
        }

        return version;
    }
}
```

### /src/main/java/com/in28minutes/rest/webservices/restfulwebservices/versioning/VersioningPersonControllerV2.java New

```java
package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* Starting Spring Framework 7 `version` attribute has been introduced.
  */
@RestController
public class VersioningPersonControllerV2 {

  /** Path-Segment Versioning **/
  // @GetMapping("/v1/person")
  // http://localhost:8080/v1/person
  @GetMapping(value = "/{version}/person", version = "1.0")
  public PersonV1 getFirstVersionOfPerson() {
  return new PersonV1("Bob Charlie");
  }

  // @GetMapping("/v2/person")
  @GetMapping(value = "/{version}/person", version = "2.0")
  public PersonV2 getSecondVersionOfPerson() {
  return new PersonV2(new Name("Bob", "Charlie"));
  }

  /** Query Parameter Versioning **/
  // http://localhost:8080/person?version=1
  @GetMapping(path = "/person", params = "version=1")
  public PersonV1 getFirstVersionOfPersonRequestParameter() {
  return new PersonV1("Bob Charlie");
  }

  // http://localhost:8080/person?version=2
  @GetMapping(path = "/person", params = "version=2")
  public PersonV2 getSecondVersionOfPersonRequestParameter() {
  return new PersonV2(new Name("Bob", "Charlie"));
  }

  /** Request Header Versioning **/
  // USAGE: GET /person/header
  // X-API-Version: 1.0
  @GetMapping(path = "/person/header", version = "1.0")
  public PersonV1 getFirstVersionOfPersonRequestHeader() {
  return new PersonV1("Bob Charlie");
  }

  // USAGE: GET /person/header
  // X-API-Version: 2.0
  @GetMapping(path = "/person/header", version = "2.0")
  public PersonV2 getSecondVersionOfPersonRequestHeader() {
  return new PersonV2(new Name("Bob", "Charlie"));
  }

  @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
  public PersonV1 getFirstVersionOfPersonAcceptHeader() {
  return new PersonV1("Bob Charlie");
  }

  @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
  public PersonV2 getSecondVersionOfPersonAcceptHeader() {
  return new PersonV2(new Name("Bob", "Charlie"));
  }

}
```

## Verify

### 1. Path-Segment Versioning
- http://localhost:8080/v1/person
- http://localhost:8080/v2/person

### 2. Query Parameter Versioning
- http://localhost:8080/person?version=1
- http://localhost:8080/person?version=2

### 3. Request Header Versioning
- http://localhost:8080/person/header
  - header: X-API-Version: 1.0
- http://localhost:8080/person/header
  - header: X-API-Version: 2.0

### 4. Request Header Versioning
- http://localhost:8080/person/accept
    - header: application/vnd.company.app-v1+json
- http://localhost:8080/person/accept
    - header: application/vnd.company.app-v2+json

## References
- API Versioning: https://spring.io/blog/2025/09/16/api-versioning-in-spring
- https://www.danvega.dev/blog/spring-boot-4-api-versioning


### 2. OpenTelemetry Implementation in Spring Boot 4

In modern cloud-native applications, observability has become a fundamental requirement for both developers and architects. It helps monitor and understand application behavior, identify pitfalls, and detect issues when the application is running in production, especially in distributed environments.

To achieve architectural characteristics such as resilience and fault tolerance, observability must be included as an essential component.

Prior to Spring Boot 4, observability was achieved through **Micrometer**. Combining Spring Boot with **OpenTelemetry** provides a powerful way to capture all observability **signals—metrics, traces, and logs.** The key enabler here is the OTLP protocol, rather than any specific library.

## Why Use the Spring Boot OpenTelemetry Starter?
Spring Boot 4.0 offers three options for OpenTelemetry integration:

1. **OpenTelemetry Java Agent** - Zero code changes but has version compatibility issues
2. **3rd-party OpenTelemetry Spring Boot Starter** - External dependency
3. **Spring Boot's Official Starter** - Recommended approach with native support

The official `spring-boot-starter-opentelemetry` is the recommended approach as it:
- Integrates natively with Micrometer
- Exports signals via OTLP protocol
- Works seamlessly with Spring Boot 4.0
- Supports GraalVM native-image and AOT compilation

---

## Step-by-Step Implementation

### Step 1: Add Dependencies

Add the OpenTelemetry starter to your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-opentelemetry</artifactId>
</dependency>
```

Or in `build.gradle`:

```gradle
implementation 'org.springframework.boot:spring-boot-starter-opentelemetry'
```

**Note:** This starter includes:
- OpenTelemetry API
- `io.micrometer:micrometer-registry-otlp` for metrics
- `io.micrometer:micrometer-tracing-bridge-otel` for traces

### Step 2: Configure Metrics Export

#### 2.1 Enable Metrics Export

Add the following property to `application.properties`:

```properties
management.otlp.metrics.export.url=http://localhost:4318/v1/metrics
```

Or in `application.yml`:

```yaml
management:
  otlp:
    metrics:
      export:
        url: http://localhost:4318/v1/metrics
```

#### 2.2 Configure OpenTelemetry Semantic Conventions

Create a configuration class to use OpenTelemetry semantic conventions:

```java
@Configuration(proxyBeanMethods = false)
public class OpenTelemetryConfiguration {
    
    @Bean
    OpenTelemetryServerRequestObservationConvention openTelemetryServerRequestObservationConvention() {
        return new OpenTelemetryServerRequestObservationConvention();
    }
    
    @Bean
    OpenTelemetryJvmCpuMeterConventions openTelemetryJvmCpuMeterConventions() {
        return new OpenTelemetryJvmCpuMeterConventions(Tags.empty());
    }
    
    @Bean
    ProcessorMetrics processorMetrics() {
        return new ProcessorMetrics(
            List.of(), 
            new OpenTelemetryJvmCpuMeterConventions(Tags.empty())
        );
    }
    
    @Bean
    JvmMemoryMetrics jvmMemoryMetrics() {
        return new JvmMemoryMetrics(
            List.of(), 
            new OpenTelemetryJvmMemoryMeterConventions(Tags.empty())
        );
    }
    
    @Bean
    JvmThreadMetrics jvmThreadMetrics() {
        return new JvmThreadMetrics(
            List.of(), 
            new OpenTelemetryJvmThreadMeterConventions(Tags.empty())
        );
    }
    
    @Bean
    ClassLoaderMetrics classLoaderMetrics() {
        return new ClassLoaderMetrics(
            new OpenTelemetryJvmClassLoadingMeterConventions()
        );
    }
}
```

### Step 3: Configure Traces Export

Add the following property to enable trace export:

```properties
management.opentelemetry.tracing.export.otlp.endpoint=http://localhost:4318/v1/traces
```

Or in YAML:

```yaml
management:
  opentelemetry:
    tracing:
      export:
        otlp:
          endpoint: http://localhost:4318/v1/traces
```

Spring Boot automatically configures:
- `OtlpHttpSpanExporter` (or `OtlpGrpcSpanExporter` for gRPC)
- OpenTelemetry SDK for trace export
- Micrometer Observation API to OpenTelemetry bridge

### Step 4: Configure Logs Export

#### 4.1 Enable Log Export Property

```properties
management.opentelemetry.logging.export.otlp.endpoint=http://localhost:4318/v1/logs
```

Or in YAML:

```yaml
management:
  opentelemetry:
    logging:
      export:
        otlp:
          endpoint: http://localhost:4318/v1/logs
```

#### 4.2 Add Logback Appender Dependency

Add the OpenTelemetry Logback appender to `pom.xml`:

```xml
<dependency>
    <groupId>io.opentelemetry.instrumentation</groupId>
    <artifactId>opentelemetry-logback-appender-1.0</artifactId>
    <version>2.21.0-alpha</version> // version might change later
</dependency>
```

#### 4.3 Configure Logback Appender

Create `src/main/resources/logback-spring.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/base.xml"/>
    
    <appender name="OTEL" class="io.opentelemetry.instrumentation.logback.appender.v1_0.OpenTelemetryAppender">
    </appender>
    
    <root level="INFO">
        <appender-ref ref="OTEL"/>
    </root>
</configuration>
```

#### 4.4 Install OpenTelemetry Appender

Create a component to initialize the appender:

```java
@Component
class InstallOpenTelemetryAppender implements InitializingBean {
    
    private final OpenTelemetry openTelemetry;
    
    InstallOpenTelemetryAppender(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
    }
    
    @Override
    public void afterPropertiesSet() {
        OpenTelemetryAppender.install(this.openTelemetry);
    }
}
```

### Step 5: Context Propagation for Async Operations

When using `@Async` methods or `AsyncTaskExecutor`, context gets lost in new threads. Fix this by configuring the `ContextPropagatingTaskDecorator`:

```java
@Configuration(proxyBeanMethods = false)
public class ContextPropagationConfiguration {
    
    @Bean
    ContextPropagatingTaskDecorator contextPropagatingTaskDecorator() {
        return new ContextPropagatingTaskDecorator();
    }
}
```

This ensures:
- Trace IDs are preserved in logs across threads
- Spans are correctly linked in traces
- Context is transferred to new threads automatically

### Step 6: Add Trace ID to HTTP Response Headers

Create a Servlet filter to include trace ID in response headers:

```java
@Component
public class TraceIdFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        if (response instanceof HttpServletResponse httpResponse) {
            String traceId = Span.current().getSpanContext().getTraceId();
            httpResponse.setHeader("X-Trace-Id", traceId);
        }
        
        chain.doFilter(request, response);
    }
}
```

This allows users to include the trace ID when reporting errors.

### Step 7: Distributed Tracing Across Services

For distributed tracing, ensure you **always** use builders to create HTTP clients:

#### Not Recommended:
```java
RestTemplate restTemplate = new RestTemplate(); // Context won't propagate!
```

#### Recommended:
```java
@Service
public class MyService {
    
    private final RestClient restClient;
    
    public MyService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }
    
    // Or for RestTemplate -- deprecated
    private final RestTemplate restTemplate;
    
    public MyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    
    // Or for WebClient
    private final WebClient webClient;
    
    public MyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }
}
```

Spring Boot autoconfigures these builders to automatically propagate trace context via W3C standard headers.

### Step 8: Add Custom Metrics and Traces

#### Using Micrometer's @Observed Annotation:

```java
@Service
public class HelloService {
    
    @Observed(name = "say-hello")
    public String sayHello(@SpanTag("userId") Long userId, 
                           @SpanTag("locale") String locale) {
        // Your business logic
        return "Hello from user " + userId;
    }
}
```

This creates:
- A custom metric named "say-hello"
- Span tags for userId and locale in traces

---

## Testing with Docker Compose

### Step 9: Set Up Local OTLP Backend

Create `compose.yaml` in your project root:

```yaml
services:
  otel-lgtm:
    image: grafana/otel-lgtm
    ports:
      - "3000:3000"   # Grafana UI
      - "4318:4318"   # OTLP HTTP receiver
      - "4317:4317"   # OTLP gRPC receiver
    environment:
      - GF_SECURITY_ADMIN_PASSWORD=admin
```

### Step 10: Add Docker Compose Support (Optional)

Add dependency for automatic Docker Compose integration:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-docker-compose</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

Spring Boot will automatically:
- Detect `compose.yaml`
- Run `docker compose up`
- Configure OTLP endpoints automatically

### Step 11: Run and Verify

1. Start your application
2. Access Grafana at `http://localhost:3000` (username: admin, password: admin)
3. Make requests to your application
4. View:
    - **Logs**: All application logs with trace IDs
    - **Traces**: Distributed traces with span details
    - **Metrics**: JVM metrics, custom metrics, HTTP metrics

---

## Complete Application Properties Example

```properties
# Application Name (appears in traces/metrics)
spring.application.name=my-service

# Metrics Export
management.otlp.metrics.export.url=http://localhost:4318/v1/metrics

# Traces Export
management.opentelemetry.tracing.export.otlp.endpoint=http://localhost:4318/v1/traces

# Logs Export
management.opentelemetry.logging.export.otlp.endpoint=http://localhost:4318/v1/logs

# Optional: Sampling (1.0 = 100% of traces)
management.tracing.sampling.probability=1.0
```

---

## Key Concepts to Remember

### The OTLP Protocol

The OpenTelemetry Protocol (OTLP) is what matters, not the specific library. Spring Boot uses:
- **Micrometer** for instrumentation (API)
- **OTLP** for export (Protocol)
- This allows vendor-neutral observability

### Trace Context

- Stored in `ThreadLocal` by default
- Automatically included in logs when Micrometer Tracing is on classpath
- Format: `[traceId=..., spanId=...]`

### Distributed Tracing

Context propagates across services via W3C traceparent headers when using properly configured HTTP clients.

---

## Troubleshooting

### Context Lost in Async Methods
- Ensure `ContextPropagatingTaskDecorator` is configured

### Trace IDs Missing in Logs
- Verify Micrometer Tracing is on classpath
- Check Logback appender is properly installed

### Distributed Tracing Not Working
- Always inject and use `RestClient.Builder`, `RestTemplateBuilder`, or `WebClient.Builder`
- Never create clients with `new`

### Logs Not Appearing
- Verify OTLP endpoint is reachable
- Check Logback appender configuration
- Ensure `OpenTelemetryAppender.install()` is called

---
## Additional Resources
- OpenTelemetry with Spring Boot: https://spring.io/blog/2025/11/18/opentelemetry-with-spring-boot
- Spring Boot Documentation: https://docs.spring.io
- OpenTelemetry Documentation: https://opentelemetry.io
- Micrometer Documentation: https://micrometer.io

---

_Happy Learning @in28minutes_
