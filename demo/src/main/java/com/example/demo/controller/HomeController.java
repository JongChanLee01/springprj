package com.example.demo.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("index")
	public String index(Model model) {
		model.addAttribute("user","강감찬");
		model.addAttribute("message","안녕하세요");
		model.addAttribute("now", new Date());
		return "index"; // index.jsp
		// /WEB-INF/views/ 폴더에서 찾는다.
	}
	
	@GetMapping({"","/"})
	public String index2(Model model) {
		model.addAttribute("user","이순신");
		model.addAttribute("message","환영합니다.");
		model.addAttribute("now", new Date());
		return "index"; // index.jsp
		// /WEB-INF/views/ 폴더에서 찾는다.
	}
	
	@GetMapping("hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("date1")
	public String date1() {
		return "date1";
	}
	
	@GetMapping("loop1")
	public String loop1() {
		return "loop1";
	}
	
	@GetMapping("if1")
	public String if1() {
		return "if1";
	}
	
	@GetMapping("gugu")
	public String gugu() {
		return "gugu";
	}
}
