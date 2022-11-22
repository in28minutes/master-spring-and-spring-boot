<!---
Current Directory : /Users/ranga/Ranga/git/00.courses/master-spring-and-spring-boot/72-oauth
-->

## Complete Code Example


### /src/main/java/com/in28minutes/learnoauth/HelloWorldResource.java

```java
package com.in28minutes.learnoauth;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
	
	@GetMapping("/")
	public String helloWorld(Authentication authentication) {
		System.out.println(authentication);
		System.out.println(authentication.getPrincipal());
		return "Hello World";
	}

}
```
---

### /src/main/java/com/in28minutes/learnoauth/LearnOauthApplication.java

```java
package com.in28minutes.learnoauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnOauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnOauthApplication.class, args);
	}

}
```
---

### /src/main/java/com/in28minutes/learnoauth/OauthSecurityConfiguration.java

```java
package com.in28minutes.learnoauth;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class OauthSecurityConfiguration {

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests().anyRequest().authenticated();
		//http.formLogin();
		//http.httpBasic();
		http.oauth2Login(Customizer.withDefaults());
		return http.build();
	}

}
```
---

### /src/main/resources/application.properties

```properties
##Google API console
##http://localhost:8080/login/oauth2/code/google

spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_SECRET

```
---

### /src/test/java/com/in28minutes/learnoauth/LearnOauthApplicationTests.java

```java
package com.in28minutes.learnoauth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnOauthApplicationTests {

	@Test
	void contextLoads() {
	}

}
```
---
