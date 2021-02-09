package com.medicos.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicos.interfaceService.IMedicoService;
import com.medicos.interfaces.JPatient;
import com.medicos.model.Medico;
import com.medicos.model.Patient;

@Controller
@RequestMapping("")
public class MainController {
	@Autowired
	private IMedicoService medics;
	@GetMapping("")
	public String welcome(@RequestParam(required = false) String filter, Model model) {
		List<Medico> listMedic = medics.list();
		if (filter != null) {
			List<Medico> listMedic2 = new ArrayList<Medico>();
			for (Medico medico : listMedic) {
				if (medico.getSpecialty().equals(filter)) {
					listMedic2.add(medico);
				}
			}
			model.addAttribute("listMedic", listMedic2);
			return "index";
		}
		model.addAttribute("listMedic", listMedic);
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
