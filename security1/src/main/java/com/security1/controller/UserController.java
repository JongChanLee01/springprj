package com.security1.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class UserController {
    @GetMapping("user/index")
    public String index(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("컨텍스트 홀더:"+authentication.toString());
        log.info("MyUserDetails:"+authentication.getPrincipal());
        log.info("사용자 이름:"+authentication.getName());

        model.addAttribute("username", authentication.getName());
        return "user/index";
    }

    @GetMapping("user/redirect")
    public String redirect(Model model, HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN"))
            return "redirect:/admin/index";
        if (request.isUserInRole("ROLE_PROFESSOR")) {
            return "redirect:/professor/index";
        }
        return "redirect:/user/index";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("user/admin_only")
    public String admin_only(Model model) {
        return "user/admin_only";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDenied(HttpServletRequest request, Exception exception){
        return "error/accessDenied";
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping("user/professor_only")
    public String professor_only(Model model) {
        return "user/professor_only";
    }

}
