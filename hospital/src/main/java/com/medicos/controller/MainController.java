package com.medicos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicos.interfaces.JPatient;
import com.medicos.model.Patient;

@Controller
@RequestMapping("")
public class MainController {
	@GetMapping("")
	public String welcome() {
		return "index";
	}
	@GetMapping("/about")
	public String about() {
		return "general/about";
	}
	@GetMapping("/contact")
	public String contact() {
		return "general/contact";
	}
//	Parte usuarios
	@Autowired
    private JPatient jpatient;
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new Patient());
	    return "User/register";
	}
	@PostMapping("/process_register")
	public String processRegister(Patient user) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    String encodedPassword = passwordEncoder.encode(user.getPassword());
	    user.setPassword(encodedPassword);
	     
	    jpatient.save(user);
	     
	    return "User/register_success";
	}
	@GetMapping("/users")
	public String listUsers(Model model) {
	    List<Patient> listUsers = jpatient.findAll();
	    model.addAttribute("listUsers", listUsers);
	     
	    return "register_success";
	}
}
