package com.medicos.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medicos.interfaceService.IMedicineService;
import com.medicos.model.Medicine;
import com.medicos.model.User;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
	private IMedicineService service;
	
	@GetMapping(value="/list")
	public String findAll(@RequestParam Map<String,Object>params, Model model) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString())-1):0; 
		
		PageRequest pageRequest = PageRequest.of(page,5);
		
		Page<Medicine> pageMedicine = service.getAll(pageRequest);
		
		int totalPage = pageMedicine.getTotalPages();
		if(totalPage>0) {
			List<Integer> pages = IntStream.rangeClosed(1,totalPage).boxed().collect(Collectors.toList());
			model.addAttribute("pages",pages);
		}
		model.addAttribute("list",pageMedicine.getContent());
		
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
		Optional<Medicine> medicine=service.findById(id);
		model.addAttribute("medicine",medicine);
		return "Medicine/MedicineformMod";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/medicine/list";
	}
	
	@PostMapping("/stock")
	public String changeStock(Model model,@RequestParam int id,@RequestParam(required=false) Integer stock, RedirectAttributes flash) {
		if (stock != null) {
			Optional<Medicine> medicina = service.findById(id);
			Medicine medicin = medicina.get();
			medicin.aumentarStock(stock);
			service.save(medicin);
			flash.addFlashAttribute("success", "Stock added successfully.");
		} else {
			flash.addFlashAttribute("error", "Type the stock to add.");
		}
		
		return "redirect:/medicine/list";
	}
}
