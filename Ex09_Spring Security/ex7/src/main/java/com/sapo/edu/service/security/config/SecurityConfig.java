package com.sapo.edu.service.security.config;

import com.sapo.edu.service.security.UserDetailsServiceImpl;
import com.sapo.edu.service.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor để inject dependencies UserDetailsServiceImpl và JwtAuthenticationFilter
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Bean để cấu hình FilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable) // Vô hiệu hóa CSRF
                .authorizeHttpRequests(
                        req->req
                                .requestMatchers(HttpMethod.GET).permitAll()                                        // Cho phép tất cả các yêu cầu GET
                                .requestMatchers("/login/**", "/register/**").permitAll()                  // Cho phép các yêu cầu đăng nhập và đăng ký
                                .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority("ADMIN")       // Yêu cầu quyền ADMIN cho các yêu cầu POST tới /admin/**
                                .requestMatchers(HttpMethod.PUT, "/admin/**").hasAuthority("ADMIN")        // Yêu cầu quyền ADMIN cho các yêu cầu PUT tới /admin/**
                                .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAuthority("ADMIN")     // Yêu cầu quyền ADMIN cho các yêu cầu DELETE tới /admin/**
                                .anyRequest().authenticated()                                                       // Tất cả các yêu cầu khác yêu cầu xác thực
                ).userDetailsService(userDetailsService)                                                            // Cung cấp UserDetailsServiceImpl để xác thực người dùng
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))                                    // Quản lý phiên không lưu trạng thái
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)               // Thêm JwtAuthenticationFilter trước UsernamePasswordAuthenticationFilter
                .build();
    }

    // Bean để cung cấp PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean để cung cấp AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
