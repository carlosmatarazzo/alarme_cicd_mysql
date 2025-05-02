package br.com.fiap.alarme.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() //autoriza temporariamente todas as requisições sem necessidade de token
                        // Swagger liberado no topo da lista
//                        .requestMatchers(
//                                "/swagger-ui/**",
//                                "/swagger-ui.html",
//                                "/v3/api-docs/**",
//                                "/swagger-resources/**",
//                                "/webjars/**",
//                                "/favicon.ico",
//                                "/error",
//                                "/api/teste"
//                        ).permitAll()
//
//                        // remover depois dos testes - necessário para rodar no browser
//                        .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
//
//                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
//                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
//
//                        .requestMatchers(HttpMethod.POST, "/api/recipiente").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/recipiente/*").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.PUT, "/api/recipiente").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.GET, "/api/recipiente").hasAnyRole("ADMIN", "USER")
//
//                        .requestMatchers(HttpMethod.POST, "/api/deposito").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/deposito/*").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.PUT, "/api/deposito").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.GET, "/api/deposito").hasAnyRole("ADMIN", "USER")
//
//                        .requestMatchers(HttpMethod.POST, "/api/notificacao").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/notificacao/*").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.PUT, "/api/notificacao").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.GET, "/api/notificacao").hasAnyRole("ADMIN", "USER")
//
//                        .requestMatchers(HttpMethod.POST, "/api/coleta").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.DELETE, "/api/coleta/*").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.PUT, "/api/coleta").hasAnyRole("ADMIN", "USER")
//                        .requestMatchers(HttpMethod.GET, "/api/coleta").hasAnyRole("ADMIN", "USER")
//
//                        .anyRequest().authenticated()
//
//                )
//                .addFilterBefore(
//                        verificarToken,
//                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}