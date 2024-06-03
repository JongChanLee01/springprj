package com.shop2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // permitAll() 메소드는 어떠한 보안 요구 없이 요청을 허용해준다.
        // 보안에 취약
        http.authorizeRequests((requests) ->
               requests
                   .requestMatchers("/css/**", "/js/**", "/imgs/**").permitAll()
                   .requestMatchers("/", "/members/**", "/item/**", "/images/**","/mail/**").permitAll()
                   .requestMatchers("/admin/**").hasRole("ADMIN")
                   .anyRequest().authenticated()
        );

        // 위 경로를 제외한 요청은 인증을 요구

        http.csrf((csrf)-> csrf
            .ignoringRequestMatchers(new AntPathRequestMatcher("/mail/**"))
            .ignoringRequestMatchers("/members/findId")
        );

        http.formLogin((formLogin)->
               formLogin
                   .loginPage("/members/login")
                   .defaultSuccessUrl("/",true)
                   .usernameParameter("email")
                   .failureUrl("/members/login/error").permitAll()
               )
               .logout((logout)->
                   logout
                       .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
                       .invalidateHttpSession(true)
                       .logoutSuccessUrl("/").permitAll()
               )
        ;

        http.exceptionHandling((exception)->
               exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}