package com.order1.studentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.order1.mapper.StudentMapper;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired StudentMapper studentMapper;

    // @RequestMapping("student/list")
    // public String list(Model model, Integer order) {
    //     if (order == null) order = 0;
    //     model.addAttribute("students", studentMapper.findAll(order));
    //     model.addAttribute("order", order);
    //     return "student/list";
    // }

    @RequestMapping("student/list")
    public String list(Model model,
                       @RequestParam(value="order", required=false, defaultValue="0") int order) {
        model.addAttribute("students", studentMapper.findAll(order));
        model.addAttribute("order", order);
        return "student/list";
    }


}

