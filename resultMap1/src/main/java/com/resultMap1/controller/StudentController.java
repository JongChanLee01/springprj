package com.resultMap1.controller;

import com.resultMap1.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentMapper studentMapper;

    @RequestMapping("list")
    public String list(Model model){
        model.addAttribute("students", studentMapper.findAll());
        return "student/list";
    }
}
