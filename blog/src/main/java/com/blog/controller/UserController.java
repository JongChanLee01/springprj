package com.blog.controller;

import com.blog.dto.UserForm;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class UserController {
    @PostMapping("/home5")
    public String home5(String username, String email){
        return "home";
    }

    // @GetMapping("/home2")
    // public String home2(@RequestBody User user){
    // return "users/join";
    // }
    @GetMapping("/home")
    public String home(){
        return "users/join";
    }

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @PostMapping("/home/join")
    // public String join(UserForm form){
    public String join(User user){

        // User user = form.toEntity();
        User saved = userRepository.save(user);

        return "redirect:/home/user/" + saved.getId();
    }

    @GetMapping("/home/user/{id}")
    public String detail(@PathVariable Integer id, Model model){
        User userEntity = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당유저는 없습니다. id: " + id);
        });
        model.addAttribute("user", userEntity);
        return "users/ushow";
    }

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    // logout
    @GetMapping("/user/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "/user/loginForm";
    }

}
