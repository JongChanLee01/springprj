package com.blog.config;

import com.blog.model.User;
import com.blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service  // Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 스프링이 로그인 요청을 가로챌때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 한다.
    // username이 DB에 있는지만 확인해 주면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal=userRepository.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("사용자 정보가 없습니다."));
        log.info("principal:"+ principal);

        return new PrincipalDetail(principal);
        // 시큐리티의 세션에 유저 정보가 저장이 된다.
        // 이때 리턴하는 타입이 UserDetails 타입이어야 한다.
        // 오버라이딩 하지 않으면 우리가 입력한 사용자 정보를 담아둘 수 없다.
        //  기본값인 아이디:user, 패스워드:콘솔창밖에 저장이 안된다.
    }
}