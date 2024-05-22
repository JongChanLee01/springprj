package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


// 스프링 3.0 버젼
@Configuration // 빈등록 : 스프링컨테이너에서 객체를 관리할 수 있게 하는 것
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(new AntPathRequestMatcher("/**"))
                        .permitAll())
                        .csrf((csrf) -> csrf.disable())
                        .formLogin((formLogin) -> formLogin
                        .loginPage("/auth/loginForm")
                        .loginProcessingUrl("/auth/loginProc")
                        .defaultSuccessUrl("/",true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
        ;
        return http.build();
    }
    // 스프링 시큐리티가 해당주소로 요청오는 로그인을 가로채서 대신 로그인 해 준다.

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}