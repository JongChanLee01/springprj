package com.spring4.controller;

import com.spring4.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class DepartmentController {
    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("student/departmentList")
    public String list(Model model){
        model.addAttribute("department",departmentMapper.findAll());
        return "student/departmentList";
    }
}
