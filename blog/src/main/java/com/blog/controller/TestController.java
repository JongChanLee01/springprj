package com.blog.controller;

import com.blog.model.RoleType;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.test.MyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TestController {
    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String test(Model model){
        model.addAttribute("msg","welcome2");

        return "hello";
    }

    @PostMapping("/dummy/join")
    public String join(User user){

        user.setRole(RoleType.ADMIN);;
        User saved = userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

    //@ResponseBody
    @PostMapping("/user/join")
    public ResponseEntity<MyData> join(@RequestBody String user){
        Class user1=user.getClass();  // class명 가져오기

        MyData data = new MyData();
        // data.setMsg("아이디중복");
        data.setMsg("회원가입성공");

        String msg=data.getMsg();
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }
    // key value형태가 아닌 form 데이터는 @RequestBody를 붙여준다.


    @GetMapping("user/join")
    public String join(){
        return "join";
    }
}
