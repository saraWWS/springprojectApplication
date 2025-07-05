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

    // In-memory user details for demo purposes
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("contact_user")
                .password("{noop}userpass")  // {noop} means no encoding
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("contact_admin")
                .password("{noop}adminpass")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs or enable if needed
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers(
                                "/contacts/**",
                                "/addresses/**",
                                "/tags/**",
                                "/groups/**",
                                "/social-media/**"
                        ).hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())  // Enable form login
                .httpBasic(Customizer.withDefaults()); // Enable basic auth

        return http.build();
    }
}
