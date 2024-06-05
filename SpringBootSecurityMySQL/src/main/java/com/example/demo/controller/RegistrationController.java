package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MyUser;
import com.example.demo.service.MyUserDetailService;

@RestController
public class RegistrationController {

	@Autowired
	private MyUserDetailService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/register/user")
	public ResponseEntity<String> userRegister(@RequestBody MyUser user) {
		user.setPassWord(passwordEncoder.encode(user.getPassWord()));
		userService.saveUser(user);
		return new ResponseEntity<String>("Register Successfully", HttpStatus.OK);
	}

}
