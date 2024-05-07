package com.security1.controller;

import com.security1.model.UserSignUp;
import com.security1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping({"/", "index"})
    public String index() {
        return "home/index";
    }

    @GetMapping("login")
    public String login() {
        return "home/login";
    }

    @GetMapping("signUp")
    public String signup(Model model) {
        model.addAttribute(new UserSignUp());
        return "home/signUp";
    }

    @PostMapping("signUp")
    public String signup(Model model,
                         @Valid UserSignUp userSignUp, BindingResult bindingResult)
    {
        try {
            userService.insert(userSignUp, bindingResult);
            return "redirect:signUpSuccess";
        }
        catch (Exception e) {
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            e.printStackTrace();
            return "home/signUp";
        }
    }

    @GetMapping("signUpSuccess")
    public String signupSuccess() {
        return "home/signUpSuccess";
    }

}
