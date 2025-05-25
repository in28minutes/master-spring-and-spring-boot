package com.in28minutes.rest.webservices.restfulwebservices.jwt;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {


    /**
     * Symmetric Key Approach
     * The identical key is used for both signing the token and verifying its signature.
     * In contrast, an asymmetric key pair utilizes one key for signing the token and a separate key for signature verification.
     */
    @Value("${jwt.key}")
    private String jwtKey;

    /**
     * Core interface and with InMemoryUserDetailsManager implementation which returns user-specific data.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("in28minutes")
                        .password("{noop}dummy")
                        .authorities("READ", "ROLE_USER")
                        .build());
    }

    /**
     * The configuration establishes a Servlet Filter referred to as the <code>springSecurityFilterChain</code>, which manages all security aspects of your application,
     * including the protection of application URLs, validation of submitted usernames and passwords, and redirection to the login form, among other functions.
     * For more details: <a href="https://docs.spring.io/spring-security/reference/servlet/architecture.html#servlet-securityfilterchain">SecurityFilterChain</a>
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/authenticate").permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
        http.httpBasic(withDefaults());
        http.headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    /**
     * Creates a {@link JwtEncoder} bean using the {@link NimbusJwtEncoder} implementation.
     * <p>
     * This encoder is used to generate signed JWT tokens using a symmetric key.
     * The secret key is wrapped with {@link ImmutableSecret} to make it compatible with Nimbus.
     *
     * @return a configured {@link JwtEncoder} bean for encoding JWTs
     *
     * @see org.springframework.security.oauth2.jwt.JwtEncoder
     * @see org.springframework.security.oauth2.jwt.NimbusJwtEncoder
     * @see com.nimbusds.jose.jwk.source.ImmutableSecret
     */
    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    /**
     * The Spring Authorization Server mandates the use of RSA-256 signatures for its JWTs, and you must be quite specific if you desire an alternative.
     * This is the reason why the JwtTokenService employs the MacAlgorithm.HS512 for encoding, and subsequently utilizes the same algorithm in the JwtDecoder.
     * Uses a JwtDecoder bean to validate signatures and decode tokens
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = jwtKey.getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length, "RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    // Asymmetric Key Approach
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        // https://github.com/spring-projects/spring-security/issues/1231
//        // https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data.sql.h2-web-console.spring-security
//        return httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                	.requestMatchers("/authenticate").permitAll()
//                	.requestMatchers(PathRequest.toH2Console()).permitAll() // h2-console is a servlet and NOT recommended for a production
//                    .requestMatchers(HttpMethod.OPTIONS,"/**")
//                    .permitAll()
//                    .anyRequest()
//                    .authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
//                .sessionManagement(session -> session.
//                	sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // Deprecated in SB 3.1.x
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())) // Starting from SB 3.1.x using Lambda DSL
//                .httpBasic(
//                        Customizer.withDefaults())
////                .headers(header -> { // Deprecated in SB 3.1.x
////                    header.frameOptions().sameOrigin();
////                })
//                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) // Starting from SB 3.1.x using Lambda DSL
//                .build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
//        var authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailsService);
//
//        return new ProviderManager(authenticationProvider);
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("in28minutes")
//                                .password("{noop}dummy")
//                                .authorities("read")
//                                .roles("USER")
//                                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        JWKSet jwkSet = new JWKSet(rsaKey());
//
//        return (((jwkSelector, securityContext)
//                        -> jwkSelector.select(jwkSet)));
//    }
//
//    @Bean
//    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//        return new NimbusJwtEncoder(jwkSource);
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() throws JOSEException {
//        return NimbusJwtDecoder
//                .withPublicKey(rsaKey().toRSAPublicKey())
//                .build();
//    }
//
//    @Bean
//    public RSAKey rsaKey() {
//        KeyPair keyPair = keyPair();
//
//        return new RSAKey
//                .Builder((RSAPublicKey) keyPair.getPublic())
//                .privateKey((RSAPrivateKey) keyPair.getPrivate())
//                .keyID(UUID.randomUUID().toString())
//                .build();
//    }
//
//    @Bean
//    public KeyPair keyPair() {
//        try {
//            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(2048);
//            return keyPairGenerator.generateKeyPair();
//        } catch (Exception e) {
//            throw new IllegalStateException(
//                    "Unable to generate an RSA Key Pair", e);
//        }
//    }

}


