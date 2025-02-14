# Building REST API with Spring and Spring Boot with Kotlin

![SpringBootWithKotlin.png](SpringBootWithKotlin.png)

## Steps for Running in Local IDE

To import a pre-existing Spring Boot project developed in Kotlin into IDE,
- Download the zip from [here](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/12-rest-api-kotlin) or clone the Git repository from [here](https://github.com/in28minutes/master-spring-and-spring-boot/tree/main/12-rest-api-kotlin).
- Unzip the zip file
- **Open IDE:** Start the integrated development environment and, if required, navigate to the welcome interface.
- **Import the project:** Select File > New > Project from Existing Sources.
- **Identify project directory:** Navigate to the main directory of your existing Spring Boot Kotlin project.
- **Select build system:** IDE will automatically identify whether your project utilizes Maven or Gradle; choose the appropriate option. Check that your project's build file (pom.xml for Maven)
- **Finalize import:** Click "Open," and IDE will commence the import process, identifying the Kotlin code and Spring Boot dependencies.

**Java**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.1</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.rest.webservices</groupId>
	<artifactId>restful-web-services</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>restful-web-services</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>21</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-rest-hal-explorer</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>


		<!-- <dependency> 
				<groupId>org.springdoc</groupId> 
				<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId> 
				<version>2.6.0</version>
			</dependency> 
			<dependency> 
				<groupId>com.fasterxml.jackson.dataformat</groupId> 
				<artifactId>jackson-dataformat-xml</artifactId>
			</dependency> -->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- <dependency>
			<groupId>mysql</groupId> 
			<artifactId>mysql-connector-java</artifactId> 
			</dependency> -->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>
	<pluginRepositories>
		<pluginRepository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</pluginRepository>
	</pluginRepositories>
</project>
```

**Kotlin**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.in28minutes.rest.webservices</groupId>
	<artifactId>restful-web-services</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>restful-web-services</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>21</java.version>
		<kotlin.version>1.9.25</kotlin.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-hateoas</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.module</groupId>
			<artifactId>jackson-module-kotlin</artifactId>
		</dependency>
		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-reflect</artifactId>
		</dependency>
		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-stdlib</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-rest-hal-explorer</artifactId>
		</dependency>

		<!--
			<dependency>
				<groupId>org.springdoc</groupId>
				<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
				<version>2.6.0</version>
			</dependency>
			<dependency>
					<groupId>com.fasterxml.jackson.dataformat</groupId>
					<artifactId>jackson-dataformat-xml</artifactId>
			</dependency>
		 -->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-test-junit5</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<sourceDirectory>${project.basedir}/src/main/kotlin</sourceDirectory>
		<testSourceDirectory>${project.basedir}/src/test/kotlin</testSourceDirectory>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>org.jetbrains.kotlin</groupId>
				<artifactId>kotlin-maven-plugin</artifactId>
				<configuration>
					<args>
						<arg>-Xjsr305=strict</arg>
					</args>
					<compilerPlugins>
						<plugin>spring</plugin>
						<plugin>jpa</plugin>
						<plugin>all-open</plugin>
					</compilerPlugins>
					<pluginOptions>
						<option>all-open:annotation=jakarta.persistence.Entity</option>
						<option>all-open:annotation=jakarta.persistence.MappedSuperclass</option>
						<option>all-open:annotation=jakarta.persistence.Embeddable</option>
					</pluginOptions>
				</configuration>
				<dependencies>
					<dependency>
						<groupId>org.jetbrains.kotlin</groupId>
						<artifactId>kotlin-maven-allopen</artifactId>
						<version>${kotlin.version}</version>
					</dependency>
					<dependency>
						<groupId>org.jetbrains.kotlin</groupId>
						<artifactId>kotlin-maven-noarg</artifactId>
						<version>${kotlin.version}</version>
					</dependency>
				</dependencies>
			</plugin>
		</plugins>
	</build>

</project>
```
## Key Aspects to Comprehend from the Kotlin `pom.xml`

- Within the properties section, include the `<kotlin.version>1.9.25</kotlin.version>` entry.
- **Dependencies**
  - Three Kotlin-specific libraries are essential for the configuration of a Spring Boot web application and are set up by default:
      - `jackson-module-kotlin`, which facilitates the serialization and deserialization of Kotlin classes and data classes. This library automatically supports single-constructor classes, while also accommodating those with secondary constructors or static factory methods.
      - `kotlin-reflect`, which provides reflection capabilities for Kotlin
      - `kotlin-stdlib`, which serves as the standard library for Kotlin
      - `kotlin-test-junit5` which serves as the standard testing library for Kotlin
- **Plugins**
```xml
<build>
    <sourceDirectory>${project.basedir}/src/main/kotlin</sourceDirectory>
    <testSourceDirectory>${project.basedir}/src/test/kotlin</testSourceDirectory>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <plugin>
            <groupId>org.jetbrains.kotlin</groupId>
            <artifactId>kotlin-maven-plugin</artifactId>
            <configuration>
                <args>
                    <arg>-Xjsr305=strict</arg>
                </args>
                <compilerPlugins>
                    <plugin>spring</plugin>
                    <plugin>jpa</plugin>
                    <plugin>all-open</plugin>
                </compilerPlugins>
                <pluginOptions>
                    <option>all-open:annotation=jakarta.persistence.Entity</option>
                    <option>all-open:annotation=jakarta.persistence.MappedSuperclass</option>
                    <option>all-open:annotation=jakarta.persistence.Embeddable</option>
                </pluginOptions>
            </configuration>
            <dependencies>
                <dependency>
                    <groupId>org.jetbrains.kotlin</groupId>
                    <artifactId>kotlin-maven-allopen</artifactId>
                    <version>${kotlin.version}</version>
                </dependency>
                <dependency>
                    <groupId>org.jetbrains.kotlin</groupId>
                    <artifactId>kotlin-maven-noarg</artifactId>
                    <version>${kotlin.version}</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```
## Maven `<build>` Configuration for Kotlin + Spring Boot
This configuration sets up a **Spring Boot** application using **Kotlin** and **Maven**.
## **1. Source and Test Source Directories**
```xml
<sourceDirectory>${project.basedir}/src/main/kotlin</sourceDirectory>
<testSourceDirectory>${project.basedir}/src/test/kotlin</testSourceDirectory>
```

- Defines Kotlin source directories for compilation.
- Default Maven setup is for Java (src/main/java), so this explicitly configures it for Kotlin.

## 2. Plugins Section

Maven plugins extend the functionality of the build process.

### Spring Boot Maven Plugin
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
- Enables **Spring Boot application packaging**.
- Supports `spring-boot:run` for running the app.
- Helps generate fat JARs with embedded dependencies.

### Kotlin Maven Plugin

```xml
<plugin>
    <groupId>org.jetbrains.kotlin</groupId>
    <artifactId>kotlin-maven-plugin</artifactId>
    <configuration>
```
- This plugin compiles Kotlin code in a Maven project.
### Compiler Arguments
```xml
<args>
    <arg>-Xjsr305=strict</arg>
</args>
```
- `-Xjsr305=strict`: Enforces strict nullability checks for Java interop.
- Prevents `NullPointerException` by enforcing null safety.
### Compiler Plugins
```xml
<compilerPlugins>
    <plugin>spring</plugin>
    <plugin>jpa</plugin>
    <plugin>all-open</plugin>
</compilerPlugins>
```
- `spring` → Makes Kotlin classes open for Spring proxies.
- `jpa` → Prevents Hibernate issues with final Kotlin classes.
- `all-open` → Allows inheritance for specific annotations.
### Plugin Options (All-Open Plugin)

```xml
<pluginOptions>
    <option>all-open:annotation=jakarta.persistence.Entity</option>
    <option>all-open:annotation=jakarta.persistence.MappedSuperclass</option>
    <option>all-open:annotation=jakarta.persistence.Embeddable</option>
</pluginOptions>
```
- Ensures that classes annotated with:
    - `@Entity`
    - `@MappedSuperclass`
    - `@Embeddable`
- Are `not final`, so Spring and Hibernate can proxy them.

## 3. Kotlin Plugin Dependencies
Maven dependencies required for Kotlin-specific functionality.

###  Kotlin All-Open Plugin
```xml
<dependency>
    <groupId>org.jetbrains.kotlin</groupId>
    <artifactId>kotlin-maven-allopen</artifactId>
    <version>${kotlin.version}</version>
</dependency>
```
- Enables all-open to make specific Kotlin classes non-final.

### Kotlin No-Arg Plugin
```xml
<dependency>
    <groupId>org.jetbrains.kotlin</groupId>
    <artifactId>kotlin-maven-noarg</artifactId>
    <version>${kotlin.version}</version>
</dependency>
```
- Allows Kotlin classes to have no-arg constructors.
- Required for JPA entities and Hibernate compatibility.

## HellWorldBean

**Java**
```java
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;

	public HelloWorldBean(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [message=" + message + "]";
	}
}
```
### `src/main/kotlin/com/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldBean.kt`

**Kotlin**
```kotlin
package com.in28minutes.rest.webservices.restfulwebservices.helloworld

data class HelloWorldBean(var message: String)
```
- Defines a `data class` named `HelloWorldBean`.
- It has a single property `message: String` that is mutable (`var`).
- Being a data class, it automatically generates:
  - `toString()`
  - `equals()`
  - `hashCode()`
  - `copy()`
- Ideal for simple data-holding objects.

### Alternative: Using a Regular Class

```kotlin
class HelloWorldBean(var message: String) {
    override fun toString(): String {
        return "HelloWorldBean [message=$message]"
    }
}
```
- Defines a regular class with a `var message: String` property.
- Manually overrides `toString()` for custom output.
- Lacks built-in features of a `data class`.

## HellWorldController
**Java**
```java
package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World"; 
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World"); 
	}
	
	// Path Parameters
	// /users/{id}/todos/{id}  => /users/2/todos/200
	// /hello-world/path-variable/{name}
	// /hello-world/path-variable/Ranga

	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name)); 
	}

	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
		
		//return "Hello World V2"; 
		
		//1:
		//2:
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)

	}

	
}
```
**Kotlin**
### `src/main/kotlin/com/in28minutes/rest/webservices/restfulwebservices/helloworld/HelloWorldController.kt`
```kotlin

```

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
