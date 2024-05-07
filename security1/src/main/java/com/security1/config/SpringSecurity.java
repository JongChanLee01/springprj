package com.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// @Configuration을 사용하면 해당클래스안에 메소드를 bean으로 사용할 수 있다.
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true) // 메소드 활성
public class SpringSecurity {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/professor/**").hasRole("PROFESSOR")
                        .requestMatchers("/user/**").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login_processing")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/user/redirect", true)
                        .usernameParameter("loginName")
                        .passwordParameter("passwd")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout_processing"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .permitAll());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
