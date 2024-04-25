package com.resultMap2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.resultMap2.mapper.ProfessorMapper;

@Controller
@RequestMapping("professor")
public class ProfessorController {

    @Autowired ProfessorMapper professorMapper;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("professors", professorMapper.findAll());
        return "professor/list";
    }

}
