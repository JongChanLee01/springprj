package com.bbs1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bbs1.config.MyUserDetails;
import com.bbs1.entity.User;
import com.bbs1.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new MyUserDetails(user);
    }

}

