package com.blog.controller;

import com.blog.dto.UserForm;
import com.blog.model.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/home")
    public String home(){
        return "users/join";
    }

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


    // 회원가입을 할때는 인증이 필요없다. 그래서 인증이 필요없는 곳은 /auth를 붙인다.
    // 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
    // 그냥 주소가 / 이면 index.jsp 허용
    // static 이하에 있는 /js/**, /css/**, /image/**

    // @GetMapping("/user/joinForm")
    // public String joinForm(){
    //     return "user/joinForm";
    // }
    // @GetMapping("/joinForm")
    // public String joinForm(){
    //     return "user/joinForm";
    // }
    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    // @GetMapping("/user/loginForm")
    // public String loginForm(){
    //     return "user/loginForm";
    // }
    // @GetMapping("/loginForm")
    // public String loginForm(){
    //     return "user/loginForm";
    // }
    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    // 로그아웃
    // @GetMapping("/user/logout")
    // public String logOut(HttpSession session){
    //     session.invalidate();
    //     return "/user/loginForm";
    // }
    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "/user/loginForm";
    }


    // 회원 정보 수정
    // @GetMapping("/user/updateForm")
    // public String updateForm(){
    //     return "user/updateForm";
    // }

    // @GetMapping("/user/updateForm")
    // public String updateForm(Model model){
    //     model.addAttribute("principal",session.getAttribute("principal"));
    //     return "/user/updateForm";
    // }
    @GetMapping("/user/form")
    public String updateForm(Model model){

        // SecurityContext securityContext = SecurityContextHolder.getContext();
        // Authentication authentication = securityContext.getAuthentication();
        // UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return "user/updateForm";
    }


    // 회원탈퇴
    @GetMapping("/user/deleteForm")
    public String deleteForm(Model model){
   //     model.addAttribute("principal",session.getAttribute("principal"));
        return "user/deleteForm";
    }


    // 로그인 실패시 에러처리
    @GetMapping("/auth/fail")
    public String onFailedLogin(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
        return "redirect:/auth/loginForm";
    }
}
