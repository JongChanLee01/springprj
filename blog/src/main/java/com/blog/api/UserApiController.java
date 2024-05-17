package com.blog.api;

import com.blog.dto.ResponseDto;
import com.blog.model.RoleType;
import com.blog.model.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

        return new ResponseDto<Integer>(200,1);

    }
}
