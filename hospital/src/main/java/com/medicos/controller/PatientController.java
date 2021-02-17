package com.medicos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicos.interfaceService.IUserService;
import com.medicos.model.User;

@Controller
@RequestMapping("/patient")
public class PatientController {

	
	@Autowired
	private IUserService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User> patients=service.findByRole("ROLE_PATIENT");
		model.addAttribute("patients",patients);
		return "Patient/Patientindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("patient",new User());
		return "Patient/Patientform";
	}
	
	@PostMapping("/save")
	public String save(User m,Model model) {
		m.setRole("ROLE_PATIENT");
		service.save(m);
		return "redirect:/patient/list";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <User> patient=service.listId(id);
		model.addAttribute("patient",patient);
		return "Patient/Patientform";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/patient/list";
	}
}
