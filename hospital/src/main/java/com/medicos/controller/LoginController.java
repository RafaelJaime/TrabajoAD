package com.medicos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medicos.model.User;
import com.medicos.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name="error", required=false)String error, @RequestParam(name="logout",required=false) String logout) {
		
		model.addAttribute("user",new User());
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		
		return "User/login";
	}
	
	@GetMapping("/login-post")
	public String loginPost() {
		return "redirect:/";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User user, RedirectAttributes flash) {
		userService.register(user);
		flash.addFlashAttribute("Success","User registered successfully!");
		return "redirect:/";
	}
	
	@GetMapping("/auth/registerForm")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "User/register";
	}



}
