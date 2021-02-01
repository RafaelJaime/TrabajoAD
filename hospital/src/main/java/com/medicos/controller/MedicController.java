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

import com.medicos.interfaceService.IMedicoService;
import com.medicos.model.Medico;

@Controller
@RequestMapping("/medic")
public class MedicController {
	
	@Autowired
	private IMedicoService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Medico> medicos=service.list();
		model.addAttribute("medicos",medicos);
		return "Medic/Medicindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("medico",new Medico());
		return "Medic/Medicform";
	}
	
	@PostMapping("/save")
	public String save(Medico m,Model model) {
		service.save(m);
		return "redirect:/medic/list";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <Medico> medico=service.listId(id);
		model.addAttribute("medico",medico);
		return "Medic/Medicform";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/medic/list";
	}
}
