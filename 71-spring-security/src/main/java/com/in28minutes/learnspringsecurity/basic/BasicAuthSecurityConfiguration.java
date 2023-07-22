package com.in28minutes.learnspringsecurity.basic;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class BasicAuthSecurityConfiguration {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(
						auth -> {
							auth
							.anyRequest().authenticated();
						});
		
		http.sessionManagement(
						session -> 
							session.sessionCreationPolicy(
									SessionCreationPolicy.STATELESS)
						);
		
		//http.formLogin();
		http.httpBasic(withDefaults());

		http.csrf(csrf -> csrf.disable());

		//http.csrf(AbstractHttpConfigurer::disable);

		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));

		// http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
		
		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailService() {
//		
//		var user = User.withUsername("in28minutes")
//			.password("{noop}dummy")
//			.roles("USER")
//			.build();
//
//		
//		var admin = User.withUsername("admin")
//				.password("{noop}dummy")
//				.roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
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
	
}
