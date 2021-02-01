package com.medicos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paco")
public class MainController {
	@GetMapping("/")
	public String welcome() {
		return "base";
	}
}
