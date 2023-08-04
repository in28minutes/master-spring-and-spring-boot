## Spring Boot 3.1 Updates

### Spring Security deprecates non lambda DSL methods 

Recommended: Use Lambda DSL where possible.

Here's a history lesson - https://github.com/spring-projects/spring-security/issues/12629

#### Changes

```java
// http.csrf(AbstractHttpConfigurer::disable);
http.csrf(csrf -> csrf.disable());

//http.headers().frameOptions().sameOrigin();
http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));

//http.httpBasic();
http.httpBasic(withDefaults());

//.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) 
.oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()))
```

### Connecting to MySQL

Use `mysql-connector-j` instead of `mysql-connector-java` if you are using Spring Boot 3.1 or greater.

Remember: groupId is a little different (`com.mysql` instead of `mysql`)

Here's a history lesson - https://blogs.oracle.com/mysql/post/mysql-connectorj-has-new-maven-coordinates

```xml
<!-- Use this for Spring Boot 3.1 and higher -->
<dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
</dependency> 

<!-- Use this if you are using Spring Boot 3.0 or lower
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
	</dependency> 
-->

```
