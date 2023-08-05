## Spring Boot 3.1 Updates

## Spring Security deprecates non lambda DSL methods 

### **Recommended: Use Lambda DSL where possible.**

Here's a history lesson - https://github.com/spring-projects/spring-security/issues/12629

## Below are the comprehensive list of changes

## 1. httpBasic

#### AS-IS
```java
http.httpBasic();
```
#### TO-BE
```java
import static org.springframework.security.config.Customizer.withDefaults;
    
http.httpBasic(withDefaults());
```

## 2. CSRF

#### AS-IS
```java
http.csrf().disable();
```
#### TO-BE
```java
http.csrf(csrf -> csrf.disable());
```

## 3. Headers FrameOptions Disable

#### AS-IS
```java
http.headers().frameOptions().disable();
```
#### TO-BE
```java
http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));
```

## 4. Headers FrameOptions SameOrigin

#### AS-IS
```java
header.frameOptions().sameOrigin();
```
#### TO-BE
```java
header.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin());
```

#### AS-IS
```java
http.headers().frameOptions().sameOrigin();
```
#### TO-BE
```java
http.headers(headersConfigurer -> headersConfigurer
        .frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));
```

## 5. OAuth2 JWT

#### AS-IS
```java
.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
```
#### TO-BE
```java
import static org.springframework.security.config.Customizer.withDefaults;

.oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));

http.oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));

```
## Changes to the MySQL Dependency in Spring Boot 3.1.x

### 1. Dependency for Spring Boot 3.1.0 or higher

Use `mysql-connector-j` instead of `mysql-connector-java` 

>  Remember: groupId is a little different (`com.mysql` instead of `mysql`)

Here's a history lesson - https://blogs.oracle.com/mysql/post/mysql-connectorj-has-new-maven-coordinates

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
</dependency> 
```
### 2. Dependency for Spring Boot 3.0.0 or lower
```xml
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
</dependency>
```
