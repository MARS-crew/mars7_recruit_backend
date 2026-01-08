package com.mars7.mars7_recruit_backend.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final SecurityProperties securityProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults())   // HTTP 기본 인증 비활성화
                .csrf(Customizer.withDefaults())        // CSRF 보호 비활성화
                .cors(Customizer.withDefaults())        // CORS 설정
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 상태 비활성화
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())  // 모든 요청 허용
                .addFilterBefore(
                        new JwtAuthenticationFilter(securityProperties.getWhitelist(), jwtProvider),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}