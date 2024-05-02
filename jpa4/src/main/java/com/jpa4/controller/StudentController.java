package com.jpa4.controller;

import com.jpa4.entity.Department;
import com.jpa4.entity.Student;
import com.jpa4.model.StudentEdit;
import com.jpa4.repository.DepartmentRepository;
import com.jpa4.repository.StudentRepository;
import com.jpa4.service.DepartmentService;
import com.jpa4.service.StudentService;
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
public class StudentController {
    // repository는 validation에서는 안씀
    @Autowired
    StudentRepository studentRepository;
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("student/test1")
    public String test1(Model model){
        // Student student = studentRepository.findByStudentNo("201732008");
        Student student = studentRepository.findByStudentNo("201732008");
        model.addAttribute("students", student);
        return "student/list";
    }


    @RequestMapping("student/test2")
    public String test2(Model model) {
        model.addAttribute("students",
                studentRepository.findByName("김영수"));
        return "student/list";
    }
    @RequestMapping("student/test3")
    public String test3(Model model) {
        model.addAttribute("students",
                studentRepository.findByNameStartsWith("김"));
        return "student/list";
    }

    @RequestMapping("student/test4")
    public String test4(Model model) {
        model.addAttribute("students",
                studentRepository.findByDepartmentName("소프트웨어공학과"));
        return "student/list";
    }

    @RequestMapping("student/test5")
    public String test5(Model model) {
        model.addAttribute("students",
                studentRepository.findByDepartmentNameStartsWith("소프"));
        return "student/list";
    }
    @RequestMapping("student/test6")
    public String test6(Model model) {
        model.addAttribute("students",
                studentRepository.findByOrderByName());
        return "student/list";
    }

    @RequestMapping("student/test7")
    public String test7(Model model) {
        model.addAttribute("students",
                studentRepository.findByOrderByNameDesc());
        return "student/list";
    }

    @RequestMapping("student/test8")
    public String test8(Model model) {
        model.addAttribute("students",
                studentRepository.findByDepartmentIdOrderByNameDesc(1));
        return "student/list";
    }

    @RequestMapping("student/list")
    public String list(Model model) {
        // List<Student> student = studentRepository.findAll();
        // 최신 아이디가 먼저 나오게 하기
        List<Student> student = studentRepository.findAllByOrderByIdDesc();

        // 학번으로 오름차순 정렬하기
        // List<Student> student = studentRepository.findAllByOrderByStudentNo();
        model.addAttribute("students", student);
        return "student/list";
    }

    @GetMapping("student/create")
    public String create(Model model) {
        // Student student = new Student();
        StudentEdit studentEdit = new StudentEdit();

        // List<Department> departments = departmentRepository.findAll();
        List<Department> departments = departmentService.findAll();

        // model.addAttribute("student", student);
        model.addAttribute("studentEdit", studentEdit);

        model.addAttribute("departments", departments);
        return "student/edit";
    }

    // @PostMapping("student/create")
    // public String create(Model model, Student student) {
    //     studentRepository.save(student);
    //     return "redirect:list";
    // }
    @PostMapping("student/create")
    public String create(Model model, @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            model.addAttribute("department", departmentService.findAll());
            return "student/edit";
        }
        Student student2 = studentService.findByStudentNo(studentEdit.getStudentNo());
        
        if (student2 != null) {
            bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
            model.addAttribute("departments", departmentService.findAll());
            return "student/edit";
        }
        studentService.insert(studentEdit);
        return "redirect:list";
    }

    @GetMapping("student/edit")
    public String edit(Model model, int id) {
        // Student student = studentRepository.findById(id).get();
        StudentEdit studentEdit = studentService.findOne(id);

        // List<Department> departments = departmentRepository.findAll();
        List<Department> departments = departmentService.findAll();
        // model.addAttribute("student", student);
        model.addAttribute("studentEdit", studentEdit);
        model.addAttribute("departments", departments);
        return "student/edit";
    }

    // @PostMapping("student/edit")
    // public String edit(Model model, Student student) {
    //     studentRepository.save(student);
    //     return "redirect:list";
    // }
    @PostMapping("student/edit")
    public String edit(Model model,
                       @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "student/edit";
        }
        Student student2 = studentService.findByStudentNo(studentEdit.getStudentNo());
        if (student2 != null && student2.getId() != studentEdit.getId()) {
            bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
            model.addAttribute("departments", departmentService.findAll());
            return "student/edit";
        }
        studentService.update(studentEdit);
        return "redirect:list";
    }


    @GetMapping("student/delete")
    public String delete(Model model, int id) {
        // studentRepository.deleteById(id);
        studentService.delete(id);
        return "redirect:list";
    }


    // 교수

    // 학교 교수가 이몽룡인 학생들
    @RequestMapping("student/test9")
    public String test9(Model model) {
        model.addAttribute("students",
                studentRepository.findByDepartmentProfessorsName("이몽룡"));
        return "student/list";
    }

    
    // 수강 과목이 자료구조인 학생들
    @RequestMapping("student/test10")
    public String test10(Model model) {
        model.addAttribute("students",
                studentRepository.findBySugangsLectureTitle("자료구조"));
        return "student/list";
    }


    @RequestMapping("test1")
    public List<Object[]> test11() {
        return studentRepository.findSugangCount();
    }

    @RequestMapping("test2")
    public List<Student> test12() {
        return studentRepository.findByLectureTite("자료구조");
    }

    @RequestMapping("test3")
    public List<Student> test13() {
        return studentRepository.findByProfessorIdOrProfessorName(0, "이몽룡");
    }

    @RequestMapping("test4")
    public List<Student> test14() {
        return studentRepository.findByProfessorNameOrProfessorId(null, 13);
    }

}