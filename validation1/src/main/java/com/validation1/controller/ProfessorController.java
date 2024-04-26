package com.validation1.controller;

import com.validation1.dto.Department;
import com.validation1.dto.Professor;
import com.validation1.model.ProfessorEdit;
import com.validation1.service.DepartmentService;
import com.validation1.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("professor")
public class ProfessorController {
    @Autowired
    ProfessorService professorService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("list")
    public String list(Model model) {
        List<Professor> professors = professorService.findAll();
        model.addAttribute("professors", professors);
        System.out.println(professors);
        return "professor/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        ProfessorEdit professorEdit = new ProfessorEdit();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("professorEdit", professorEdit);
        model.addAttribute("departments", departments);
        return "professor/edit";
    }

    @PostMapping("create")
    public String create(Model model,
                         @Valid ProfessorEdit professorEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }

        Professor professor2 = professorService.findByOffice(professorEdit.getOffice());
        if (professor2 != null && professor2.getOffice() != professorEdit.getOffice()) {
            bindingResult.rejectValue("office", null, "사무실이 중복됩니다.");
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }

        professorService.insert(professorEdit);
        return "redirect:list";
    }

    @GetMapping("edit")
    public String edit(Model model, int id) {
        ProfessorEdit professorEdit = professorService.findOne(id);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("professorEdit", professorEdit);
        model.addAttribute("departments", departments);
        return "professor/edit";
    }

    // @Valid는 bean validator를 이용해서 객체의 제약 조건을 검증하도록 지시하는 어노테이션
    @PostMapping("edit")
    public String edit(Model model,
                       @Valid ProfessorEdit professorEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }
        Professor professor2 = professorService.findByOffice(professorEdit.getOffice());
        if (professor2 != null && professor2.getOffice() != professorEdit.getOffice()) {
            bindingResult.rejectValue("office", null, "사무실이 중복됩니다.");
            model.addAttribute("departments", departmentService.findAll());
            return "professor/edit";
        }

        professorService.update(professorEdit);
        return "redirect:list";
    }

    @GetMapping("delete")
    public String delete(Model model, int id) {
        professorService.delete(id);
        return "redirect:list";
    }
}
