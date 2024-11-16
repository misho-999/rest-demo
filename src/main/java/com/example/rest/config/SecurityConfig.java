package com.example.rest.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/users/all").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/users/all-as-page").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cars/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/cars/by-user-id/").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/users/*").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users/*").permitAll()

                        .requestMatchers(HttpMethod.PATCH, "/users/{id}").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/users/{id}").permitAll()

                         //Actuator
//                        .requestMatchers(HttpMethod.GET,"/actuator/health").permitAll()
//                        .requestMatchers(HttpMethod.GET,"/actuator/**").hasRole("ADMIN")
                        .requestMatchers(EndpointRequest.to(HealthEndpoint.class)).permitAll()
                        .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")

                        .anyRequest().authenticated())
                .httpBasic(withDefaults())            // Enable HTTP Basic Authentication
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
