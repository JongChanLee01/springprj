package com.security1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

    @GetMapping("professor/index")
    public String index(Model model) {
        return "professor/index";
    }

}
