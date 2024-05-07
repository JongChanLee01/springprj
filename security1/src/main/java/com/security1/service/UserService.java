package com.security1.service;

import com.security1.dto.User;
import com.security1.mapper.UserMapper;
import com.security1.model.UserSignUp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper = new ModelMapper();

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public void insert(UserSignUp userSignUp, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new Exception("사용자를 등록할 수 없습니다.");
        if (userSignUp.getPasswd1().equals(userSignUp.getPasswd2()) == false) {
            bindingResult.rejectValue("passwd2", null, "비밀번호가 일치하지 않습니다.");
            throw new Exception("사용자를 등록할 수 없습니다.");
        }
        User user = userMapper.findByLoginName(userSignUp.getLoginName());
        if (user != null) {
            bindingResult.rejectValue("loginName", null, "사용자 아이디가 중복됩니다.");
            throw new Exception("사용자를 등록할 수 없습니다.");
        }
        User newUser = modelMapper.map(userSignUp, User.class);
        newUser.setPassword(passwordEncoder.encode(userSignUp.getPasswd1()));
        newUser.setEnabled(true);
        userMapper.insert(newUser);
    }
}
