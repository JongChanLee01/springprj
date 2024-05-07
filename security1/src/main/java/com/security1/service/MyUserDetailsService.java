package com.security1.service;

import com.security1.config.MyUserDetails;
import com.security1.dto.User;
import com.security1.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// @RequiredArgsConstructor  -> private final UserMapper userMapper; 사용하기 위해서씀
@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    // 의존성 주입하는 방법 두가지
    // 1)
    private final UserMapper userMapper;
    
    // 2)
    // @Autowired
    // UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByLoginName(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new MyUserDetails(user);
    }

}
