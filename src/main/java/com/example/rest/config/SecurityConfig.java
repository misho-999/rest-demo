package com.example.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/users/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cars/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/*").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/users/*").permitAll()

                        .requestMatchers(HttpMethod.PATCH, "/users/{id}").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()

                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
