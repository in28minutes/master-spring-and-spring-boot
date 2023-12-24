# Spring Security Step By Step Changes

## Step 06

- Step 00 - Getting started with Spring Security
- Step 01 - Understanding Security Fundamentals
- Step 02 - Understanding Security Principles
- Step 03 - Getting Started with Spring Security
- Step 04 - Exploring Default Spring Security Configuration
- Step 05 - Creating Spring Boot Project for Spring Security
- Step 06 - Exploring Spring Security - Form Authentication


### /src/main/java/com/in28minutes/learnspringsecurity/HelloWorldResource.java

```java
package com.in28minutes.learnspringsecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

}
```
---

### /src/main/java/com/in28minutes/learnspringsecurity/LearnSpringSecurityApplication.java

```java
package com.in28minutes.learnspringsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringSecurityApplication.class, args);
	}

}
```
---

### /src/main/resources/application.properties

```properties
spring.main.banner-mode=off
logging.pattern.console= %d{MM-dd HH:mm:ss} - %logger{36} - %msg%n

```
---

### /src/test/java/com/in28minutes/learnspringsecurity/LearnSpringSecurityApplicationTests.java

```java
package com.in28minutes.learnspringsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnSpringSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---

### /build.gradle
```
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.2.0'
	id 'io.spring.dependency-management' version '1.1.0'
}

group = 'com.in28minutes'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '21'

repositories {
	mavenCentral()
	maven { url 'https://repo.spring.io/milestone' }
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation 'org.springframework.boot:spring-boot-devtools'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
}

tasks.named('test') {
	useJUnitPlatform()
}
```

## Step 07

- Step 07 - Exploring Spring Security - Basic Authentication

### /src/main/java/com/in28minutes/learnspringsecurity/HelloWorldResource.java Modified
New Lines
```
return "Hello World v1";
```
---

### /src/main/resources/application.properties Modified
New Lines
```
spring.security.user.name=in28minutes
spring.security.user.password=dummy
```
## Step 08

### /src/main/java/com/in28minutes/learnspringsecurity/HelloWorldResource.java Moved to
### /src/main/java/com/in28minutes/learnspringsecurity/resources/HelloWorldResource.java New

---

### /src/main/java/com/in28minutes/learnspringsecurity/resources/TodoResource.java New

```java
package com.in28minutes.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final List<Todo> TODOS_LIST = 
			List.of(new Todo("in28minutes", "Learn AWS"),
					new Todo("in28minutes", "Get AWS Certified"));

	@GetMapping("/todos")
	public List<Todo> retrieveAllTodos() {
		return TODOS_LIST;
	}

	@GetMapping("/users/{username}/todos")
	public Todo retrieveTodosForSpecificUser(@PathVariable String username) {
		return TODOS_LIST.get(0);
	}

	@PostMapping("/users/{username}/todos")
	public void createTodoForSpecificUser(@PathVariable String username
			, @RequestBody Todo todo) {
		logger.info("Create {} for {}", todo, username);
	}

}

record Todo (String username, String description) {}
```
---

## Step 10

- Step 08 - Exploring Spring Security - Cross Site Request Forgery - CSRF
- Step 09 - Exploring Spring Security - CSRF for REST API
- Step 10 - Creating Spring Security Configuration to Disable CSRF

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java New

```java
package com.in28minutes.learnspringsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class BasicAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth.anyRequest().authenticated();
						});
		
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						);
		
		//http.formLogin();
		http.httpBasic(withDefaults());
		
		http.csrf(csrf -> csrf.disable());
		
		return http.build();
	}


}
```
---

### /src/main/java/com/in28minutes/learnspringsecurity/resources/SpringSecurityPlayResource.java New

```java
package com.in28minutes.learnspringsecurity.resources;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SpringSecurityPlayResource {
	
	@GetMapping("/csrf-token")
	public CsrfToken retrieveCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}

}
```
---

## Step 12

- Step 11 - Exploring Spring Security - Getting Started with CORS
- Step 12 - Exploring Spring Security - Storing User Credentials in memory

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java Modified

```java

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class BasicAuthSecurityConfiguration {
	

	@Bean
	public UserDetailsService userDetailService() {
		
		var user = User.withUsername("in28minutes")
			.password("{noop}dummy")
			.roles("USER")
			.build();

		
		var admin = User.withUsername("admin")
				.password("{noop}dummy")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
	
	
}
```
---

### /src/main/resources/application.properties Modified
New Lines
```
#spring.security.user.name=in28minutes
#spring.security.user.password=dummy
```
## Step 13

- Step 13 - Exploring Spring Security - Storing User Credentials using JDBC

### /build.gradle

```
implementation 'org.springframework.boot:spring-boot-starter-jdbc'
implementation 'com.h2database:h2'
```

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java Modified

```java
package com.in28minutes.learnspringsecurity.basic;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@Configuration
public class BasicAuthSecurityConfiguration {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource) {
		
		var user = User.withUsername("in28minutes")
			.password("{noop}dummy")
			.roles("USER")
			.build();
		
		var admin = User.withUsername("admin")
				.password("{noop}dummy")
				.roles("ADMIN", "USER")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}

	
}
```
---

### /src/main/resources/application.properties Modified
New Lines
```
spring.datasource.url=jdbc:h2:mem:testdb
```
## Step 15

- Step 14 - Understanding Encoding vs Hashing vs Encryption
- Step 15 - Exploring Spring Security - Storing Bcrypt Encoded Passwords

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java Modified

```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class BasicAuthSecurityConfiguration {

	
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource) {
		
		var user = User.withUsername("in28minutes")
			//.password("{noop}dummy")
			.password("dummy")
			.passwordEncoder(str -> passwordEncoder().encode(str))
			.roles("USER")
			.build();
		
		var admin = User.withUsername("admin")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN", "USER")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	
}
```
---

## Step 18

- Step 16 - Getting Started with JWT Authentication
- Step 17 - Setting up JWT Auth with Spring Security and Spring Boot - 1
- Step 18 - Setting up JWT Auth with Spring Security and Spring Boot - 2

### /build.gradle

```
implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'
```


### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java Modified

Comment out Basic Auth Configuration

```
//@Configuration
```

---

### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtSecurityConfiguration.java New

```java
package com.in28minutes.learnspringsecurity.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
public class JwtSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth
							.requestMatchers("/authenticate").permitAll()
							.anyRequest().authenticated();
						});
		
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						);
		
		http.httpBasic(withDefaults());
		
		http.csrf(csrf -> csrf.disable());
		
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
		
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
		
		
		return http.build();
	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailService(DataSource dataSource) {
		
		var user = User.withUsername("in28minutes")
			//.password("{noop}dummy")
			.password("dummy")
			.passwordEncoder(str -> passwordEncoder().encode(str))
			.roles("USER")
			.build();
		
		var admin = User.withUsername("admin")
				//.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder(str -> passwordEncoder().encode(str))
				.roles("ADMIN", "USER")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public KeyPair keyPair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		
		return new RSAKey
				.Builder((RSAPublicKey)keyPair.getPublic())
				.privateKey(keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString())
				.build();
	}

	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		var jwkSet = new JWKSet(rsaKey);
		
		return (jwkSelector, context) ->  jwkSelector.select(jwkSet);
		
	}
	
	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder
				.withPublicKey(rsaKey.toRSAPublicKey())
				.build();
		
	}
	
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
}

```
---

## Step 19

- Step 19 - Setting up JWT Resource with Spring Security and Spring Boot - 1

### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtAuthenticationResource.java New

```java
package com.in28minutes.learnspringsecurity.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {
	
	@PostMapping("/authenticate") 
	public Authentication authenticate(Authentication authentication) {
		return authentication;
	}

}
```
---

## Step 20

- Step 20 - Setting up JWT Resource with Spring Security and Spring Boot - 2

### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtAuthenticationResource.java Modified

```java
package com.in28minutes.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	@PostMapping("/authenticate") 
	public JwtRespose authenticate(Authentication authentication) {
		return new JwtRespose(createToken(authentication));
	}

	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
								.issuer("self")
								.issuedAt(Instant.now())
								.expiresAt(Instant.now().plusSeconds(60 * 30))
								.subject(authentication.getName())
								.claim("scope", createScope(authentication))
								.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims))
						.getTokenValue();
	}

	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
			.map(a -> a.getAuthority())
			.collect(Collectors.joining(" "));			
	}

}

record JwtRespose(String token) {}
```
---

## Step 22

- Step 21 - Understanding Spring Security Authentication
- Step 22 - Exploring Spring Security Authorization

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java Modified
New Lines
```
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
```
---

### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtAuthenticationResource.java Modified
Disable JWT Resource
```
//@RestController
							.getTokenValue();
```


### /src/main/java/com/in28minutes/learnspringsecurity/jwt/JwtSecurityConfiguration.java Modified
Disable JwtSecurityConfiguration
```
//@Configuration
```

### /src/main/java/com/in28minutes/learnspringsecurity/resources/TodoResource.java Modified
New Lines
```
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.annotation.security.RolesAllowed;

	@PreAuthorize("hasRole('USER') and #username == authentication.name")
	@PostAuthorize("returnObject.username == 'in28minutes'")
	@RolesAllowed({"ADMIN", "USER"})
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public Todo retrieveTodosForSpecificUser(@PathVariable String username) {
			return TODOS_LIST.get(0);
	}
```
