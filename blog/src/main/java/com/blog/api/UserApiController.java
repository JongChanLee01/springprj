package com.blog.api;

import com.blog.dto.ResponseDto;
import com.blog.model.RoleType;
import com.blog.model.User;
import com.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // @PostMapping("api/user")
    // public ResponseDto<Integer> save(@RequestBody User user){
    //     user.setRole(RoleType.ADMIN);
    //     int result = userService.회원가입(user);
    //
    //     return new ResponseDto<Integer>(200,result);
    // }
    @PostMapping("auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){

        user.setRole(RoleType.ADMIN);
        int result= userService.회원가입(user);

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

    @GetMapping("/api/user/{username}")
    public ResponseEntity<String> check(@PathVariable String username){

        int result=userService.중복확인(username);

        if(result==1){  // null
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("NO");
        }
    }

    // key=value, x-www-form-urlencode의 형태로 데이터를 받고 싶으면 @RequestBody를 생략한다.
    // JSON형태로 받고 싶으면 @RequestBody를 붙인다.
    // @PutMapping("/user")
    // public ResponseDto<Integer> update(@RequestBody User user){
    //     userService.회원수정(user);
    //     // 여기서는 트랜잭션이 종료되기 때문에 DB에 값은 변경이 돼있다.
    //     // 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해준다.
    //
    //     return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    // }

    // 회원정보수정
    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.회원수정(user);

        // 세션등록
        // Authentication authentication=authenticationManager
        //         .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authentication=authenticationManager.authenticate(auth);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    // 회원탈퇴
    @DeleteMapping("/user/delete/{id}")
    public ResponseDto<Integer> delete(@PathVariable Integer id, @RequestBody User user){
        int result= userService.회원탈퇴(id, user);
        if(result==1){
            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }else{
            return new ResponseDto<Integer>(HttpStatus.NO_CONTENT.value(), -1);
        }
    }
}
