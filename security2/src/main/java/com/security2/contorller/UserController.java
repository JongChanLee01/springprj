package com.security2.contorller;

import com.security2.config.MyUserDetails;
import com.security2.dto.User;
import com.security2.model.UserOAuth2SignUp;
import com.security2.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("info1")
    public String info1(Model model) {
        return "user/info1";
    }

    @GetMapping("info2")
    public String info2(Model model) {
        return "user/info2";
    }

    @GetMapping("oauth2signup")
    public String OAuth2SignUp(Model model, OAuth2AuthenticationToken auth) {
        User user = userService.findByOAuth2Id(auth);
        if (user != null) {
            oauth2login(user, auth);
            return "redirect:info1";
        } else {
            UserOAuth2SignUp userOAuth2SignUp = userService.createUserOAuth2SignUp(auth);
            model.addAttribute("userOAuth2SignUp", userOAuth2SignUp);
            return "user/OAuth2SignUp";
        }
    }

    @PostMapping("oauth2signup")
    public String OAuth2SignUp(Model model, OAuth2AuthenticationToken auth,
                               @Valid UserOAuth2SignUp userOAuth2SignUp, BindingResult bindingResult) {
        try {
            User user = userService.insert(auth, userOAuth2SignUp, bindingResult);
            oauth2login(user, auth);
            return "redirect:info1";
        } catch (Exception e) {
            log.error("OAuth2SignUp 에러", e);
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "user/OAuth2SignUp";
        }
    }

    void oauth2login(User user, OAuth2AuthenticationToken auth) {
        var userDetails = new MyUserDetails(user, auth.getPrincipal().getAttributes());
        var newAuth = new OAuth2AuthenticationToken(userDetails,
                userDetails.getAuthorities(), auth.getAuthorizedClientRegistrationId());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}