package com.br.MovieFlix.MovieFlix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // CSRF - Desabilitado para APIs REST com stateless session
                .csrf(csrf -> csrf.disable())

                // Session - Stateless (ideal para APIs REST)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Headers de Segurança
                .headers(headers ->
                        headers
                                .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'"))
                                .frameOptions(frame -> frame.deny())
                                .xssProtection(xss -> xss.and().headerValue("1; mode=block"))
                                .contentTypeOptions(cto -> cto.and())
                                .cacheControl(cache -> cache.and())
                )

                // Configurar URLs Públicas e Privadas
                .authorizeHttpRequests(auth ->
                        auth
                                // Públicas
                                .requestMatchers("/api/auth/**").permitAll()
                                .requestMatchers("/api/public/**").permitAll()
                                .requestMatchers("/api/swagger-ui.html").permitAll()
                                .requestMatchers("/api/swagger-ui/**").permitAll()
                                .requestMatchers("/api/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/movies/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/streamings/**").permitAll()

                                // Atuador (Health Check)
                                .requestMatchers("/api/actuator/**").permitAll()

                                // Todas as outras requisições requerem autenticação
                                .anyRequest().authenticated()
                )

                // Tratamento de Exceções
                .exceptionHandling(exception ->
                        exception
                                .authenticationEntryPoint((request, response, authException) -> {
                                    response.setContentType("application/json");
                                    response.setStatus(401);
                                    response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Authentication required\"}");
                                })
                                .accessDeniedHandler((request, response, accessDeniedException) -> {
                                    response.setContentType("application/json");
                                    response.setStatus(403);
                                    response.getWriter().write("{\"error\": \"Forbidden\", \"message\": \"Access denied\"}");
                                })
                )

                // HTTP Basic Auth (desenvolvimento apenas)
                .httpBasic(basic -> basic.and());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

