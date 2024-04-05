package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Form1Controller {
	
	// index
	@GetMapping({"","/"})
	public String index() {
		return "index"; // index
	}
	
	// localhost:8088/form1/text1
	/*
	 * @GetMapping("form1/text1") public String text1(Model model) { return
	 * "form1/text1"; //form1 -> text1.jsp }
	 */
	
	@GetMapping("form1/text1")
	public String text2(Model model, String name, String age) {
		model.addAttribute("param1",name);
		model.addAttribute("param2",age);
		return "form1/text1"; //form1 -> text1.jsp
	}
	
	@PostMapping("form1/text1")
	public String text1(Model model, String param1, String param2) {
		System.out.println(param1);
		System.out.println(param2);
		return "form1/text1"; //form1 -> text1.jsp
	}
	
	@GetMapping("form1/text2")
	public String text2(Model model) {
		model.addAttribute("param1","hello");
		model.addAttribute("param2","world");
		model.addAttribute("now",new Date());
		
		return "form1/text2";
	}
}
