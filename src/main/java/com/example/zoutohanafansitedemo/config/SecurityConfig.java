package com.example.zoutohanafansitedemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF保護を無効化（POST/PUT/DELETEで403を防ぐ）
                .csrf(csrf -> csrf.disable())
                // 全てのリクエストを許可
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // ログインフォーム等も無効化
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
