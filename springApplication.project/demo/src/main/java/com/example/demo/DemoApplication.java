package com.contactsaver.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails normalUser = User.withDefaultPasswordEncoder()
				.username("contact_user")
				.password("userpass")
				.roles("USER")
				.build();

		UserDetails adminUser = User.withDefaultPasswordEncoder()
				.username("contact_admin")
				.password("adminpass")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(normalUser, adminUser);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/public/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()

						.requestMatchers("/admin/**").hasRole("ADMIN")

						.requestMatchers(
								"/contacts/**",
								"/tags/**",
								"/addresses/**",
								"/groups/**",
								"/social-media/**"
						).hasAnyRole("USER", "ADMIN")

						.anyRequest().authenticated()
				)
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}