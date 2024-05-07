package com.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("admin/index")
    public String index(Model model) {
        return "admin/index";
    }
}
