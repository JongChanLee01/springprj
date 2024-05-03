package com.jpa4.controller;

import com.jpa4.entity.Department;
import com.jpa4.entity.Student;
import com.jpa4.model.Pagination;
import com.jpa4.model.StudentEdit;
import com.jpa4.repository.DepartmentRepository;
import com.jpa4.repository.StudentRepository;
import com.jpa4.service.DepartmentService;
import com.jpa4.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    // @RequestMapping("student/list")
    // public String list(Model model) {
    //     // List<Student> student = studentRepository.findAll();
    //     // 최신 아이디가 먼저 나오게 하기
    //     List<Student> student = studentRepository.findAllByOrderByIdDesc();
    //
    //     // 학번으로 오름차순 정렬하기
    //     // List<Student> student = studentRepository.findAllByOrderByStudentNo();
    //     model.addAttribute("students", student);
    //     return "student/list";
    // }

    // @RequestMapping("student/list")
    // public String list(Model model, Pagination pagination) {
    //     List<Student> student = studentService.findAll(pagination);
    //
    //     model.addAttribute("students", student);
    //     return "student/list";
    // }

    @GetMapping("student/list")
    public String list(Model model, Pagination pagination, HttpServletRequest request) {
        pagination.setUrl(request.getRequestURL().toString());
        List<Student> students = studentService.findAll(pagination);
        model.addAttribute("students", students);

        model.addAttribute("orders", studentService.getOrders());

        return "student/list";
    }



    // @GetMapping("student/create")
    // public String create(Model model) {
    //     // Student student = new Student();
    //     StudentEdit studentEdit = new StudentEdit();
    //
    //     // List<Department> departments = departmentRepository.findAll();
    //     List<Department> departments = departmentService.findAll();
    //
    //     // model.addAttribute("student", student);
    //     model.addAttribute("studentEdit", studentEdit);
    //
    //     model.addAttribute("departments", departments);
    //     return "student/edit";
    // }
    @GetMapping("student/create")
    public String create(Model model, Pagination pagination) {
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
    // public String create(Model model, @Valid StudentEdit studentEdit, BindingResult bindingResult) {
    public String create(Model model, Pagination pagination, @Valid StudentEdit studentEdit, BindingResult bindingResult) {

        // if(bindingResult.hasErrors()){
        //     model.addAttribute("department", departmentService.findAll());
        //     return "student/edit";
        // }
        // Student student2 = studentService.findByStudentNo(studentEdit.getStudentNo());
        //
        // if (student2 != null) {
        //     bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
        //     model.addAttribute("departments", departmentService.findAll());
        //     return "student/edit";
        // }

        // if (studentService.hasErrors(studentEdit, bindingResult)) {
        //     model.addAttribute("departments", departmentService.findAll());
        //     return "student/edit";
        // }
        //
        // studentService.insert(studentEdit);
        // return "redirect:list";

        try {
            // studentService.insert(studentEdit, bindingResult);
            // return "redirect:list";
            studentService.insert(studentEdit, bindingResult, pagination);
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("departments", departmentService.findAll());
            bindingResult.rejectValue("", null, "등록할 수 없습니다.");
            return "student/edit";
        }

    }

    @GetMapping("student/edit")
    // public String edit(Model model, int id) {
    // public String edit(Model model, int id, Pagination pagination) {
    public String edit(Model model, @RequestParam("id") int id, Pagination pagination) {
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
    // @PostMapping("student/edit")
    @PostMapping(value = "student/edit", params = "cmd=save")
    // public String edit(Model model,
    //                    @Valid StudentEdit studentEdit, BindingResult bindingResult) {
    public String edit(Model model, Pagination pagination,
                       @Valid StudentEdit studentEdit, BindingResult bindingResult) {
        // if (bindingResult.hasErrors()) {
        //     model.addAttribute("departments", departmentService.findAll());
        //     return "student/edit";
        // }
        // Student student2 = studentService.findByStudentNo(studentEdit.getStudentNo());
        // if (student2 != null && student2.getId() != studentEdit.getId()) {
        //     bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
        //     model.addAttribute("departments", departmentService.findAll());
        //     return "student/edit";
        // }

        // if (studentService.hasErrors(studentEdit, bindingResult)) {
        //     model.addAttribute("departments", departmentService.findAll());
        //     return "student/edit";
        // }
        //
        //
        // studentService.update(studentEdit);
        // return "redirect:list";

        try {
            studentService.update(studentEdit, bindingResult);
            // return "redirect:list";
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("departments", departmentService.findAll());
            bindingResult.rejectValue("", null, "수정할 수 없습니다.");
            return "student/edit";
        }
    }


    // @GetMapping("student/delete")
    // public String delete(Model model, int id) {
    //     // studentRepository.deleteById(id);
    //     studentService.delete(id);
    //     return "redirect:list";
    // }
    @PostMapping(value="student/edit", params="cmd=delete")
    // public String delete(Model model,
    //                      StudentEdit studentEdit, BindingResult bindingResult) {
    public String delete(Model model, Pagination pagination,
            StudentEdit studentEdit, BindingResult bindingResult) {
        try {
            studentService.delete(studentEdit.getId());
            // return "redirect:list";
            return "redirect:list?" + pagination.getQueryString();
        }
        catch (Exception e) {
            model.addAttribute("departments", departmentService.findAll());
            bindingResult.rejectValue("", null, "삭제할 수 없습니다.");
            return "student/edit";
        }
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
