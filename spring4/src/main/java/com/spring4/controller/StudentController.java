package com.spring4.controller;

import com.spring4.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class StudentController {
    @Autowired
    StudentMapper studentMapper; // DI 의존성 주입

    @RequestMapping("student/list")
    public String list(Model model) {
        model.addAttribute("students", studentMapper.findAll());
        return "student/list";
    }
}
