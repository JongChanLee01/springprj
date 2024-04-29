package com.mybatis4.controller;

import java.util.List;

import com.mybatis4.model.Pagination;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import com.mybatis4.dto.Department;
import com.mybatis4.dto.Student;
import com.mybatis4.model.StudentEdit;
import com.mybatis4.service.DepartmentService;
import com.mybatis4.service.StudentService;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired StudentService studentService;
    @Autowired DepartmentService departmentService;

    @GetMapping("list")
    public String list(Model model, Pagination pagination, HttpServletRequest request) {
        // List<Student> students = studentService.findAll();

        log.info("get URL {} ", request.getRequestURL());

        pagination.setUrl(request.getRequestURL().toString());
        List<Student> students = studentService.findAll(pagination);
        model.addAttribute("students", students);
        return "student/list";
    }

    @GetMapping("create")
    public String create(Model model, Pagination pagination) {
        StudentEdit studentEdit = new StudentEdit();
        List<Department> departments = departmentService.findAll();
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departments);
        return "student/edit";
    }

    @PostMapping("create")
    public String create(Model model,
                         @Valid StudentEdit studentEdit, BindingResult bindingResult, Pagination pagination) {
        try {
            studentService.insert(studentEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
            // return "redirect:list";
        }
        catch (Exception e) {
            model.addAttribute("departments", departmentService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "student/edit";
        }
    }

    @GetMapping("edit")
    public String edit(Model model, int id, Pagination pagination) {
        StudentEdit studentEdit = studentService.findOne(id);
        List<Department> departments = departmentService.findAll();
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departments);
        return "student/edit";
    }




    @PostMapping(value="edit", params="cmd=save")
    public String edit(Model model,
                       @Valid StudentEdit studentEdit, BindingResult bindingResult, Pagination pagination) {
        try {
            studentService.update(studentEdit, bindingResult);
            return "redirect:list?" + pagination.getQueryString();
            //return "redirect:list";
        }
        catch (Exception e) {
            model.addAttribute("departments", departmentService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "student/edit";
        }
    }

    @PostMapping(value="edit", params="cmd=delete")
    public String delete(Model model, Pagination pagination,
                         StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            studentService.delete(studentEdit.getId());
            return "redirect:list?" + pagination.getQueryString();
            //return "redirect:list";
        }
        catch (Exception e) {
            model.addAttribute("departments", departmentService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "student/edit";
        }
    }
}
