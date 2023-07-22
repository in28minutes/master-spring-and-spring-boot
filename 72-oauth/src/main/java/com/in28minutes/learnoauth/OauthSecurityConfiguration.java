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
		// http.authorizeHttpRequests().anyRequest().authenticated();
		http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

		//http.formLogin();
		//http.httpBasic();
		http.oauth2Login(Customizer.withDefaults());
		return http.build();
	}

}
