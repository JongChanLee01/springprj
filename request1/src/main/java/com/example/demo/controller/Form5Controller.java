package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.Member;

import jakarta.servlet.http.HttpSession;

@Controller
public class Form5Controller {
	@GetMapping("form5/register1")
    public String register1(Model model) {
        return "form5/register1";
    }

    @PostMapping("form5/register1")
    public String register1(Model model, HttpSession session, String userid, String name,
            String password1, String password2, String email, int departmentId)
    {
        String errorMsg = null;
        if (userid == null || userid.length() == 0)
            errorMsg = "사용자 아이디를 입력하세요";
        else if (name == null || name.length() == 0)
            errorMsg = "이름을 입력하세요";
        else if (password1 == null || password1.length() == 0)
            errorMsg = "비밀번호1을 입력하세요";
        else if (password2 == null || password2.length() == 0)
            errorMsg = "비밀번호2를 입력하세요";
        else if (password1.equals(password2) == false)
            errorMsg = "비밀번호 불일치";
        else if (email == null || email.length() == 0)
            errorMsg = "이메일 주소를 입력하세요";
        else {
            session.setAttribute("userid", userid);
            session.setAttribute("name", name);
            session.setAttribute("email", email);
            return "redirect:register_success1";
        }
        model.addAttribute("userid", userid);
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("departmentId", departmentId);
        model.addAttribute("errorMsg", errorMsg);
        return "form5/register1";
    }
 
    @RequestMapping("form5/register_success1")
    public String register_success1(Model model) {
        return "form5/register_success1";
    }
    
    @GetMapping("form5/register2")
    public String register2(Model model) {
        return "form5/register2";
    }
 
    @PostMapping("form5/register2")
    public String register2(Model model, HttpSession session, Member member)
    {
        String errorMsg = null;
        if (member.getUserid() == null || member.getUserid().length() == 0)
            errorMsg = "사용자 아이디를 입력하세요";
        else if (member.getName() == null || member.getName().length() == 0)
            errorMsg = "이름을 입력하세요";
        else if (member.getPassword1() == null || member.getPassword1().length() == 0)
            errorMsg = "비밀번호1을 입력하세요";
        else if (member.getPassword2() == null || member.getPassword2().length() == 0)
            errorMsg = "비밀번호2를 입력하세요";
        else if (member.getPassword1().equals(member.getPassword2()) == false)
            errorMsg = "비밀번호 불일치";
        else if (member.getEmail() == null || member.getEmail().length() == 0)
            errorMsg = "이메일 주소를 입력하세요";
        else {
            session.setAttribute("member", member);
            return "redirect:register_success2";
        }
        model.addAttribute("member", member);
        model.addAttribute("errorMsg", errorMsg);
        return "form5/register2";
    }
 
    @RequestMapping("form5/register_success2")
    public String register_success2(Model model) {
        return "form5/register_success2";
    }
}
