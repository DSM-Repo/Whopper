package com.example.whopper.global.config;

import com.example.whopper.global.error.GlobalExceptionFilter;
import com.example.whopper.global.security.jwt.JwtFilter;
import com.example.whopper.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenProvider jwtTokenProvider;

    private final ObjectMapper objectMapper;

    @Override
    public void configure(HttpSecurity http) {

        JwtFilter jwtTokenFilter = new JwtFilter(jwtTokenProvider);
        GlobalExceptionFilter globalExceptionFilter = new GlobalExceptionFilter(objectMapper);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(globalExceptionFilter, JwtFilter.class);
    }
}
