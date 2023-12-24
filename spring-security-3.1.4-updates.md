## Spring Security Changes - Spring Boot 3.1.X versions ONLY

> These changes are NOT NEEDED for 3.2.X and above!

## Below are the comprehensive list of changes

## 1. Basic Authentication Config

#### AS-IS
```java
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll());

    http.csrf(csrf -> csrf.disable());

    http.httpBasic(withDefaults());

    return http.build();
}

```

#### TO-BE
```java

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    RequestMatcher optionsMatcher = new AntPathRequestMatcher("/**", HttpMethod.OPTIONS.toString());
    http.authorizeHttpRequests(auth -> auth
            .requestMatchers(optionsMatcher).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/**")).authenticated());

    http.csrf(csrf -> csrf.disable());

    http.httpBasic(withDefaults());

    return http.build();
}
```
## 2. JWT Security Config
#### AS-IS

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    // https://github.com/spring-projects/spring-security/issues/1231
    // https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.h2-web-console.spring-security
    return httpSecurity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/authenticate").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll() // h2-console is a servlet and NOT recommended for a production
                .requestMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll()
                .anyRequest()
                .authenticated())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.
                sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // Deprecated in SB 3.1.x
            .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults())) // Starting from SB 3.1.x using Lambda DSL
            .httpBasic(
                    Customizer.withDefaults())
//                .headers(header -> { // Deprecated in SB 3.1.x
//                    header.frameOptions().sameOrigin();
//                })
            .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Starting from SB 3.1.x using Lambda DSL
            .build();
}
````

#### TO-BE
```java
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
    HandlerMappingIntrospector handlerMappingIntrospector) throws Exception {

    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(handlerMappingIntrospector);
    // https://github.com/spring-projects/spring-security/issues/1231
    // https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.h2-web-console.spring-security
    return httpSecurity.authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(mvcMatcherBuilder.pattern("/authenticate")).permitAll()
                    .requestMatchers(mvcMatcherBuilder.pattern("/hello-world")).permitAll()
                    .requestMatchers(mvcMatcherBuilder.pattern("/hello-world-bean")).permitAll()
                    .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                    .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.OPTIONS,"/**"))
                    .permitAll()
                    .anyRequest()
                    .authenticated())
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session -> session.
                    sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()))
                    .httpBasic(withDefaults())
                    .headers(header -> header.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()))
                    .build();

}
```
### Changes

- `HandlerMappingIntrospector` injected as a parameter
- Added this line `MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(handlerMappingIntrospector);`
- `.requestMatchers("/authenticate").permitAll()` changed to `.requestMatchers(mvcMatcherBuilder.pattern("/authenticate")).permitAll()`
- `.requestMatchers(PathRequest.toH2Console()).permitAll() ` changed to `.requestMatchers(antMatcher("/h2-console/**")).permitAll()` 

For more details, please refer the following links
- https://github.com/jzheaux/cve-2023-34035-mitigations
- https://github.com/spring-projects/spring-security/issues/13568
- https://spring.io/security/cve-2023-34035