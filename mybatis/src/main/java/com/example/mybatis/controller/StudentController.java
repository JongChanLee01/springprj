package com.example.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.mybatis.dto.Department;
import com.example.mybatis.dto.Student;
import com.example.mybatis.mapper.DepartmentMapper;
import com.example.mybatis.mapper.StudentMapper;

 
@Controller
@RequestMapping("student")
public class StudentController {
 
    @Autowired StudentMapper studentMapper;
    @Autowired DepartmentMapper departmentMapper;
 
    @GetMapping("list")
    public String list(Model model) {
        List<Student> students = studentMapper.findAll();
        model.addAttribute("students", students);
        return "student/list";
    }
 
    @GetMapping("create")
    public String create(Model model) {
        Student student = new Student();
        List<Department> departments = departmentMapper.findAll();
        model.addAttribute("student", student);
        model.addAttribute("departments", departments);
        return "student/edit";
    }
 
    @PostMapping("create")
    public String create(Model model, Student student) {
        studentMapper.insert(student);
        return "redirect:list";
    }
 
    @GetMapping("edit")
    public String edit(Model model, int id) {
        Student student = studentMapper.findOne(id);
        List<Department> departments = departmentMapper.findAll();
        model.addAttribute("student", student);
        model.addAttribute("departments", departments);
        return "student/edit";
    }
 
    @PostMapping("edit")
    public String edit(Model model, Student student) {
        studentMapper.update(student);
        return "redirect:list";
    }
 
    @GetMapping("delete")
    public String delete(Model model, int id) {
        studentMapper.delete(id);
        return "redirect:list";
    }
}