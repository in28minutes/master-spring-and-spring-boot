# Building REST API with Spring and Spring Boot with Kotlin

## Steps for Running in Local IDE

To import a pre-existing Spring Boot project developed in Kotlin into IntelliJ IDEA,
- Download the zip from [here](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/12-rest-api-kotlin) or clone the Git repository from [here](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/12-rest-api-kotlin).
- Unzip the zip file
- **Open IntelliJ IDEA:** Start the integrated development environment and, if required, navigate to the welcome interface.
- **Import the project:** Select File > New > Project from Existing Sources.
- **Identify project directory:** Navigate to the main directory of your existing Spring Boot Kotlin project.
- **Select build system:** IntelliJ will automatically identify whether your project utilizes Maven or Gradle; choose the appropriate option. Check that your project's build file (pom.xml for Maven)
- **Finalize import:** Click "Open," and IntelliJ will commence the import process, identifying the Kotlin code and Spring Boot dependencies.

**Java**
```java
package com.in28minutes.rest.webservices.restfulwebservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulWebServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfulWebServicesApplication.class, args);
    }

}
```
**Kotlin**

```kotlin
package com.in28minutes.rest.webservices.restfulwebservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestfulWebServicesApplication

fun main(args: Array<String>) {
    runApplication<RestfulWebServicesApplication>(*args)
}
```
