package com.sucheong.platform.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .cors() // CORS 활성화
//                .and()
//                .csrf().disable() // CSRF 보호 비활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/v2/api-docs/**",
                                "/webjars/**",
                                "/swagger-ui/index.html").permitAll()
                        .anyRequest().authenticated()
                );

    //                .formLogin()
//                .and()
//                .logout().logoutSuccessUrl("/")
//                .and();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring()
                    .requestMatchers("/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html",
                            "/swagger-ui/**",
                            "/swagger-resources/**",
                            "/v2/api-docs/**",
                            "/webjars/**",
                            "/swagger-ui/index.html")
                    .anyRequest(); // 필터를 타면 안되는 경로
        };
    }
}
