package com.in28minutes.learnspringsecurity.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
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

import static org.springframework.security.config.Customizer.withDefaults;


//@Configuration
//@EnableWebSecurity
public class JwtSecurityConfiguration {

	/**
	 * Symmetric Key Approach
	 * The identical key is used for both signing the token and verifying its signature.
	 * In contrast, an asymmetric key pair utilizes one key for signing the token and a separate key for signature verification.
	 */
	@Value("${jwt.key}")
	private String jwtKey;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.sessionManagement(session ->
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
		);

		http.httpBasic(withDefaults());
		http.csrf(AbstractHttpConfigurer::disable);
		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

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
	JwtEncoder jwtEncoder() {
		return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
	}

	/**
	 * The Spring Authorization Server mandates the use of RSA-256 signatures for its JWTs, and you must be quite specific if you desire an alternative.
	 * This is the reason why the JwtTokenService employs the MacAlgorithm.HS512 for encoding, and subsequently utilizes the same algorithm in the JwtDecoder.
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
//	@Bean
//	public KeyPair keyPair() {
//		try {
//			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//			keyPairGenerator.initialize(2048);
//			return keyPairGenerator.generateKeyPair();
//		} catch (Exception ex) {
//			throw new RuntimeException(ex);
//		}
//	}
//
//	@Bean
//	public RSAKey rsaKey(KeyPair keyPair) {
//
//		return new RSAKey
//				.Builder((RSAPublicKey)keyPair.getPublic())
//				.privateKey(keyPair.getPrivate())
//				.keyID(UUID.randomUUID().toString())
//				.build();
//	}
//
//	@Bean
//	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
//		var jwkSet = new JWKSet(rsaKey);
//
//		return (jwkSelector, context) ->  jwkSelector.select(jwkSet);
//
//	}
//
//	@Bean
//	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
//		return NimbusJwtDecoder
//				.withPublicKey(rsaKey.toRSAPublicKey())
//				.build();
//
//	}
//
//	@Bean
//	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
//		return new NimbusJwtEncoder(jwkSource);
//	}
}
