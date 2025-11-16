# Spring Boot 4 Features

### API Versioning
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
