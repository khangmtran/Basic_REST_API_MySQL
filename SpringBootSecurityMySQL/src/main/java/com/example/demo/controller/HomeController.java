package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/admin")
	public String adminPage() {
		return "I am an admin";
	}
	
	@GetMapping("/user")
	public String userPage() {
		return "I am a user";
	}
	
	@GetMapping("/home")
	public String welcomePage() {
		return "Welcome";
	}
	
	@GetMapping("/something")
	public String somethingPage() {
		return "Something";
	}
	
}
