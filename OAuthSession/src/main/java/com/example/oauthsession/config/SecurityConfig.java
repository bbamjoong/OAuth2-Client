package com.example.oauthsession.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // csrf 끄기
        http
                .csrf((csrf) -> csrf.disable());

        // formLogin 끄기
        http
                .formLogin((login) -> login.disable());

        // HttpBasic 끄기
        http
                .httpBasic((basic) -> basic.disable());

        // client 방식이 아닌 login 방식을 이용 (좀 더 쉬움)
        http
                .oauth2Login(Customizer.withDefaults());

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
