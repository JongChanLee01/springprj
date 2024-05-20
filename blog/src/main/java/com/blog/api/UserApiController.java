package com.blog.api;

import com.blog.dto.ResponseDto;
import com.blog.model.RoleType;
import com.blog.model.User;
import com.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        user.setRole(RoleType.ADMIN);
        int result = userService.회원가입(user);

        return new ResponseDto<Integer>(200,result);

    }

    @PostMapping("api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){

        User principal=userService.로그인(user); // principal 접근주체

        System.out.println(principal);
        if(principal !=null){
            session.setAttribute("principal",principal);
            return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
        }else{
            return new ResponseDto<Integer>(HttpStatus.NO_CONTENT.value(),0);
        }
    }
}
