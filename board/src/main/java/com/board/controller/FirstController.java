package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceTo(Model model){
        model.addAttribute("username", "이순신");
        return "greetings";
        //templates/greetings.mustache
    }

    @GetMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam(value = "msg", required = false)String msg){
        return msg;
    }
    // localhost:8088/hello2?msg=lee

}
