package com.example.btlnhom14.Config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity(debug = true)
@RequiredArgsConstructor
public class SecurityConfig {

	
    @Autowired
	private final JwtAuthenticationFilter jwtAuthFilter;
	
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth -> auth

                                //auth
                                .requestMatchers("/api/auth/**").permitAll()
                              

                                //user-answer
                                .requestMatchers("/api/user-answer/**").permitAll()

                                //question
                                .requestMatchers(HttpMethod.GET, "/api/question/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/question/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/question/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/question/**").hasAnyAuthority("ROLE_ADMIN")

                                //exam result
                                .requestMatchers(HttpMethod.GET, "/api/result/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/result/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/result/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/result/**").hasAnyAuthority("ROLE_ADMIN")

                                //exam attempt
                                .requestMatchers("/api/attempt/**").permitAll()

                                //exam
                                .requestMatchers(HttpMethod.GET, "/api/exam/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/exam/**").hasAnyAuthority("ROLE_ADMIN")

                                //user
                                .requestMatchers("/api/user/**").hasAnyAuthority("ROLE_ADMIN")
                                .requestMatchers("/api/**").hasAnyAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
