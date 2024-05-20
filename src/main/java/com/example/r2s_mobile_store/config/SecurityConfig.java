package com.example.r2s_mobile_store.config;

import org.springframework.security.config.Customizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.r2s_mobile_store.repository.UserRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
            UserDetailsService userDetailsService,
            JwtAuthFilter jwtAuthFilter) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // Vô hiệu hóa CSRF trong cấu hình bảo mật

        httpSecurity.sessionManagement(configure -> configure.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Cấu
                                                                                                                      // hình
                                                                                                                      // chế
                                                                                                                      // độ
                                                                                                                      // tạo
                                                                                                                      // phiên
                                                                                                                      // không
                                                                                                                      // trạng
                                                                                                                      // thái
                .authenticationProvider(authenticationProvider(userDetailsService)) // Cấu hình AuthenticationProvider
                                                                                    // để xác thực người dùng
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Thêm JwtAuthFilter trước
                                                                                             // UsernamePasswordAuthenticationFilter
                                                                                             // trong chuỗi bộ lọc

        httpSecurity
                // Bắt đầu cấu hình xác thực các yêu cầu HTTP
                .authorizeHttpRequests(auths -> auths
                        .requestMatchers("/api/users", "/api/login").permitAll() // Cho phép truy cập không xác thực
                                                                                 // vào
                        // các API "/api/users" và "/api/login"
                        .requestMatchers(HttpMethod.GET, "/api/category/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/product/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/variant-product/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/thumbnail/**").permitAll()
                        .requestMatchers("/api/order/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/order-detail/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers("/api/upload/**").permitAll()
                        .requestMatchers("/api/payment/**").hasRole("MEMBER")
                        .anyRequest().authenticated()) // Yêu cầu xác thực cho tất cả các yêu cầu còn lại
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" +
                        username));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
