package com.jpa4.controller;

import com.jpa4.entity.Department;
import com.jpa4.entity.Professor;
import com.jpa4.repository.DepartmentRepository;
import com.jpa4.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("professor/list")
    public String list(Model model){
        List<Professor> professor = professorRepository.findAll();
        model.addAttribute("professors", professor);
        return "professor/list";
    }

    @GetMapping("professor/create")
    public String create(Model model) {
        Professor professor = new Professor();
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("professor", professor);
        model.addAttribute("departments", departments);
        return "professor/edit";
    }

    @PostMapping("professor/create")
    public String create(Model model, Professor professor) {
        professorRepository.save(professor);
        return "redirect:list";
    }

    @GetMapping("professor/edit")
    public String edit(Model model, int id) {
        Professor professor = professorRepository.findById(id).get();
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("professor", professor);
        model.addAttribute("departments", departments);
        return "professor/edit";
    }

    @PostMapping("professor/edit")
    public String edit(Model model, Professor professor) {
        professorRepository.save(professor);
        return "redirect:list";
    }

    @GetMapping("professor/delete")
    public String delete(Model model, int id) {
        professorRepository.deleteById(id);
        return "redirect:list";
    }
}
