package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Form3Controller {
	@GetMapping("form3/check1")
    public String check1(Model model) {
        return "form3/check1";
    }
 
    @PostMapping("form3/check1")
    public String check1(Model model, Boolean checkbox1, String radio1, String radio2) {
        model.addAttribute("checkbox1", checkbox1);
        model.addAttribute("radio1", radio1);
        model.addAttribute("radio2", radio2);
        return "form3/check1";
    }
}
