package com.security1.config;

import com.security1.dto.User;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    final boolean accountNonExpired = true;
    final boolean accountNonLocked = true;
    final boolean credentialsNonExpired = true;
    final String password;
    final String username;
    final boolean isEnabled;
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    final String name;
    final String email;
    final String userType;

    public MyUserDetails(User user) {
        switch (user.getUserType()) {
            case "교수": authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR")); break;
            case "학생": authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT")); break;
            case "관리자": authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); break;
        }
        this.username = user.getLoginName();
        this.password = user.getPassword();
        this.isEnabled = user.isEnabled();

        this.name = user.getName();
        this.email = user.getEmail();
        this.userType = user.getUserType();
    }
}
