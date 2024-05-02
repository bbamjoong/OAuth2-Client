package com.example.oauthsession.config;

import com.example.oauthsession.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

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
        // customOAuth2UserService 주입.
        http
                .oauth2Login((oauth2) -> oauth2
                        // 커스텀 로그인 페이지 추가
                        .loginPage("/login")
                        .userInfoEndpoint((userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))));

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }
}
