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

import com.medicos.interfaceService.IMedicineService;
import com.medicos.model.Medicine;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
	private IMedicineService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Medicine> medicine=service.list();
		model.addAttribute("medicines",medicine);
		return "Medicineindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("medicine",new Medicine());
		return "Medicineform";
	}
	
	@PostMapping("/save")
	public String save(Medicine m,Model model) {
		service.save(m);
		return "redirect:/medicine/list";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <Medicine> medicine=service.listId(id);
		model.addAttribute("medicine",medicine);
		return "Medicineform";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/medicine/list";
	}
}
