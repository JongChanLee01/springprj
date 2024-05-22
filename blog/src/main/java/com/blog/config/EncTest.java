package com.blog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncTest {
    private static final Logger log = LoggerFactory.getLogger(EncTest.class);

    public static void main(String[] args){
        String encPass = new BCryptPasswordEncoder().encode("1234");
        log.info("해시암호화 :" +encPass);
    }
}
