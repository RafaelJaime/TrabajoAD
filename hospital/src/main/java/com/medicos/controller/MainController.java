package com.medicos.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicos.interfaceService.IUserService;
import com.medicos.interfaces.IUser;
import com.medicos.model.User;

@Controller
@RequestMapping("")
public class MainController {
	
	@Autowired
	private IUserService user;
	
	@GetMapping("")
	public String welcomeClient() {
		return "client";
		
	}
	@GetMapping("admin")
	public String welcome(@RequestParam(required = false) String filter, Model model) {
		List<User> listMedic = user.list();
		if (filter != null) {
			List<User> listMedic2 = new ArrayList<User>();
			for (User medico : listMedic) {
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
	
	@GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new User());
        return "User/login";
    }
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "User/register";
    }
	
	@Autowired
	private IUser repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@PostMapping("/process_register")
	public String processRegister(User user) {
		User us=new User();
		us.setName(user.getName());
		us.setPassword(encoder.encode(user.getPassword()));
		us.setSpecialty("");
		us.setRole("ROLE_USER");
		User newUser = repository.save(us);
		
		return "User/register_success";
	}

}
