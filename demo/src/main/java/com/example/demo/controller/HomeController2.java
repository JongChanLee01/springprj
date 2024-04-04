package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController2 {
	
	@GetMapping("hello5")
	public String hello2() {
		return "welcome to my world";
	}
}
