package com.medicos.controller;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicos.interfaceService.IUserService;
import com.medicos.interfaces.IMedicalAppointment;
import com.medicos.model.MedicalAppointment;
import com.medicos.model.User;

@Controller
@RequestMapping("/patients")
public class Patient_controller {
	
	@Autowired
	private IMedicalAppointment medicap;
	@Autowired
	private IUserService service;
	
	@GetMapping({"/medicalAppointment", "/medicalappointment"})
	public String aks(Model model) {
		return "MedicAp/ask";
	}
	@PostMapping({"/medicalAppointment", "/medicalappointment"})
	public String saveAdd(@RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model, Principal principal) {
		MedicalAppointment cita = new MedicalAppointment();
		cita.setDate(date);
		User usuario = service.findByName(principal.getName());
		cita.setPatient(usuario);
		medicap.save(cita);
		System.out.println(date.toString());
		return "redirect:/";
	}
}
