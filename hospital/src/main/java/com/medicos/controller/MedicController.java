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
@RequestMapping("/medic")
public class MedicController {
	
	@Autowired
	private IUserService service;
	
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User> medicos=service.findByRole("ROLE_MEDIC");
		model.addAttribute("medicos",medicos);
		return "Medic/Medicindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("medico",new User());
		return "Medic/Medicform";
	}
	
	@PostMapping("/save")
	public String save(User m,Model model) {
		m.setRole("ROLE_MEDIC");
		service.save(m);
		return "redirect:/medic/list";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <User> medico=service.listId(id);
		model.addAttribute("medico",medico);
		return "Medic/MedicformMod";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/medic/list";
	}
}
