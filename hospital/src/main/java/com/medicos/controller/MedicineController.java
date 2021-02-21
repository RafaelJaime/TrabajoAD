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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medicos.interfaceService.IMedicineService;
import com.medicos.model.Medicine;
import com.medicos.model.User;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
	private IMedicineService service;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Medicine> medicine=service.list();
		model.addAttribute("medicines",medicine);
		return "Medicine/Medicineindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("medicine",new Medicine());
		return "Medicine/Medicineform";
	}
	
	@PostMapping("/save")
	public String save(Medicine m,Model model, RedirectAttributes flash) {
		service.save(m);
		flash.addFlashAttribute("success", "Medicine saved successfully.");
		return "redirect:/medicine/list";
	}
	
	@PostMapping("/saveEdit")
	public String saveEdit(Medicine m,Model model, RedirectAttributes flash) {
		Medicine oldMedicine = service.findByName(m.getName());
		oldMedicine.setName(m.getName());
		oldMedicine.setDescription(m.getDescription());
		oldMedicine.setPrescription(m.getPrescription());
		oldMedicine.setPrice(m.getPrice());
		oldMedicine.setStock(m.getStock());
		service.save(oldMedicine);
		flash.addFlashAttribute("success", "Medicine saved successfully.");
		return "redirect:/medicine/list";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <Medicine> medicine=service.listId(id);
		model.addAttribute("medicine",medicine);
		return "Medicine/MedicineformMod";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/medicine/list";
	}
	
	@PostMapping("/stock")
	public String changeStock(Model model, int id, int stock, RedirectAttributes flash) {
		System.out.println(id);
		Optional<Medicine> medicina = service.listId(id);
		Medicine medicin = medicina.get();
		medicin.aumentarStock(stock);
		service.save(medicin);
		flash.addFlashAttribute("success", "Medicine daleted successfully.");
		return "redirect:/medicine/list";
	}
}
