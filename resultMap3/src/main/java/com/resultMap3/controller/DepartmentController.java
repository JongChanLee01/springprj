package com.resultMap3.controller;

import com.resultMap3.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    DepartmentMapper departmentMapper;

    @RequestMapping("list")
    public String list(Model model){
        model.addAttribute("departments", departmentMapper.findAll());
        System.out.println(departmentMapper.findAll());
        return "department/list";
    }

}
