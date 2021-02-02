package com.medicos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {
	@GetMapping("")
	public String welcome() {
<<<<<<< HEAD
		return "base";
=======
		return "index";
	}
	@GetMapping("/about")
	public String about() {
		return "general/about";
	}
	@GetMapping("/contact")
	public String contact() {
		return "general/contact";
>>>>>>> branch 'master' of https://github.com/RafaelJaime/TrabajoAD.git
	}
}
