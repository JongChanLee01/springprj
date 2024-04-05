package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Form2Controller {
	@GetMapping("form2/select1")
    public String select1(Model model) {
        model.addAttribute("number1", 0);
        model.addAttribute("number2", 0);
        model.addAttribute("cmd", "+");
        model.addAttribute("result", 0);
        return "form2/select1";
    }
 
    @PostMapping("form2/select1")
    public String select1(Model model, Integer number1, String cmd, Integer number2) {
        model.addAttribute("number1", number1);
        model.addAttribute("number2", number2);
        model.addAttribute("cmd", cmd);
        model.addAttribute("result", calc(number1, cmd, number2));
        return "form2/select1";
    }
 
    private int calc(int number1, String cmd, int number2) {
        int result = 0;
        switch (cmd) {
        case "+": result = number1 + number2; break;
        case "-": result = number1 - number2; break;
        case "*": result = number1 * number2; break;
        case "/": result = number1 / number2; break;
        }
        return result;
    }
}
