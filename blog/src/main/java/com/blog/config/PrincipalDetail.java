package com.blog.config;

import com.blog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Data
public class PrincipalDetail implements UserDetails {
    // 컴포지션(Composition)은 객체 지향 프로그래밍에서 사용되는 개념 중 하나로,
    // 하나의 객체가 다른 객체를 포함하는 것을 의미합니다.
    private User user; // 컴포지션


    public PrincipalDetail(User user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors=new ArrayList<>();
        collectors.add(() -> {
            return "ROLE_"+user.getRole();  // 스프링 롤 규칙 ROLE_USER, 필수사항
        });
        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠기지 않음
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
