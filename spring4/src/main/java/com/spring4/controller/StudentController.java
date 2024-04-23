package com.spring4.controller;

import com.spring4.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {
    @Autowired
    StudentMapper studentMapper; // DI 의존성 주입

    @RequestMapping("student/list")
    public String list(Model model) {
        model.addAttribute("students", studentMapper.findAll());
        return "student/list";
    }

    @RequestMapping("student/list1")
    public String list1(Model model, String srchText) {
        if (srchText == null) srchText = "";
        model.addAttribute("students", studentMapper.findByName(srchText + "%"));
        model.addAttribute("srchText", srchText);
        return "student/list1";
    }

    // http://localhost:8088/student/detail/7 이런식으로 검색 /번호
    @RequestMapping("student/detail")
    //public String detail(Model model, @RequestParam("id") Integer id) {
    public String detail(Model model, Integer id) {
        if (id == null) id = 4;
        model.addAttribute("student", studentMapper.findById(id));
        return "student/detail";
    }

    
    // http://localhost:8088/student/detail?id=7 이런식으로 검색 ?id=번호
    @RequestMapping("student/detail/{id}")
    public String detail2(Model model, @PathVariable Integer id) {
        if (id == null) id = 4;
        model.addAttribute("student", studentMapper.findById(id));
        return "student/detail";
    }

    @RequestMapping("student/test1")
    public String test1(Model model) {
        return "student/test1";
    }

    @RequestMapping("student/list2")
    public String list2(Model model, String srchText) {
        if (srchText == null) srchText = "";
        model.addAttribute("students", studentMapper.findByName(srchText + "%"));
        model.addAttribute("srchText", srchText);
        return "student/list2";
    }

    @RequestMapping("student/list3")
    public String list3(Model model, String srchText) {
        if (srchText == null) srchText = "";
        model.addAttribute("students", studentMapper.findByName(srchText + "%"));
        model.addAttribute("srchText", srchText);
        return "student/list3";
    }
}
