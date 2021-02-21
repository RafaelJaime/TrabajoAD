package com.medicos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medicos.interfaceService.IUserService;
import com.medicos.model.User;

@Controller
@RequestMapping("/medic")
public class MedicController {
	
	@Autowired
	private IUserService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/edit")
	public String edit(Model model) {
		User medicos=service.findByName(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		model.addAttribute("medicos",medicos);
		return "Medic/MedicindexEdit";	
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User> medicos=service.findByRole("MEDIC");
		model.addAttribute("medicos",medicos);
		return "Medic/Medicindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("medico",new User());
		return "Medic/Medicform";
	}
	
	@PostMapping("/saveAdd")
	public String saveAdd(User m,Model model) {
		m.setRole("MEDIC");
		m.setPassword(encoder.encode(m.getPassword()));
		service.save(m);
		return "redirect:/medic/list";
	}
	
	@PostMapping("/save")
	public String save(User m,Model model, RedirectAttributes flash) {
		m.setRole("MEDIC");
		User oldUser = service.findByName(m.getName());
		if (oldUser.getName()==null) {
			oldUser.setName(m.getName());
		}
		oldUser.setFirstname(m.getFirstname());
		oldUser.setSurname(m.getSurname());
		if (m.getAge()>= 18) {
			oldUser.setAge(m.getAge());
		}
		oldUser.setDate(oldUser.getDate());
		oldUser.setSpecialty(m.getSpecialty());
		service.save(oldUser);
		flash.addFlashAttribute("success", "Medic saved successfully.");
		return "redirect:/medic/list";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <User> medico=service.listId(id);
		model.addAttribute("medico",medico);
		return "Medic/MedicformMod";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id, RedirectAttributes flash) {
		service.delete(id);
		flash.addFlashAttribute("success", "Medic deletedsuccessfully.");
		return "redirect:/medic/list";
	}
}
