//package com.tanthanh.apigateway.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Value("${jwt.secret}")
//    private String SECRET_KEY;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/api/v1/admin/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/api/v1/user/**").hasAuthority("ROLE_USER")
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter());
//
//        return http.build();
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKey key = new SecretKeySpec(SECRET_KEY.getBytes(), "HMAC");
//        return NimbusJwtDecoder.withSecretKey(key).build();
//    }
//
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        // Cấu hình converter nếu cần
//        return converter;
//    }
//
//}
