<!---
Current Directory : /Users/ranga/Ranga/git/00.courses/master-spring-and-spring-boot/20-spring-security
-->

## Complete Code Example


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

### /src/main/java/com/in28minutes/learnspringsecurity/basic/BasicAuthSecurityConfiguration.java

```java
package com.in28minutes.learnspringsecurity.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

### /src/main/java/com/in28minutes/learnspringsecurity/resources/HelloWorldResource.java

```java
package com.in28minutes.learnspringsecurity.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World v1";
	}

}
```
---

### /src/main/java/com/in28minutes/learnspringsecurity/resources/SpringSecurityPlayResource.java

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

### /src/main/java/com/in28minutes/learnspringsecurity/resources/TodoResource.java

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

### /src/main/resources/application.properties

```properties
spring.main.banner-mode=off
logging.pattern.console= %d{MM-dd HH:mm:ss} - %logger{36} - %msg%n

#spring.security.user.name=in28minutes
#spring.security.user.password=dummy
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
