package com.in28minutes.rest.webservices.restfulwebservices.security

import org.springframework.context.annotation.Configuration

@Configuration
class SpringSecurityConfiguration {

//	@Bean
//	fun filterChain(http: HttpSecurity): SecurityFilterChain {
//
//		// 1) All requests should be authenticated
//		http.authorizeRequests { auth ->
//			auth.anyRequest().authenticated()
//		}
//		// 2) If a request is not authenticated, use http basic
//		http.httpBasic(withDefaults())
//
//		// 3) CSRF -> POST, PUT
//		http.csrf { csrf ->
//			csrf.disable()
//		}
//		// OR
//		// http.csrf(AbstractHttpConfigurer::disable)
//
//		return http.build()
//	}

}