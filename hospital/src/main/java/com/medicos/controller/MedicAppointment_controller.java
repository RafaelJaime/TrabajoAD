package com.medicos.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicos.interfaceService.IUserService;
import com.medicos.interfaces.IMedicalAppointment;
import com.medicos.model.MedicalAppointment;
import com.medicos.model.User;

@Controller
@RequestMapping("/medicalAppointment")
public class MedicAppointment_controller {
	@Autowired
	private IMedicalAppointment medicap;
	@Autowired
	private IUserService service;
	
	@GetMapping("/{id}")
	public String list(@PathVariable int id,Model model) {
		MedicalAppointment appo = medicap.getOne(id);
		if (appo.getObservations() != null) {
			return "redirect:/medicalAppointment/list";
		}
		model.addAttribute("medicap", appo);
		return "MedicAp/show";
	}
	@PostMapping("/save/{id}")
	public String save(MedicalAppointment ma,Model model, Principal principal) {
		MedicalAppointment appo = medicap.getOne(ma.getId());
		appo.setObservations(ma.getObservations());
		User usuario = service.findByName(principal.getName());
		appo.setMedic(usuario);
		medicap.save(appo);
		return "redirect:/medicalAppointment/list";
	}
	@GetMapping({"/list", "/", ""})
	public String list(Model model) {
		Collection<MedicalAppointment> appointment = medicap.findByObservations(null);
		System.out.println(appointment);
		model.addAttribute("medicap", appointment);
		return "MedicAp/index";
	}
	@GetMapping("/patient")
	public String patients(Model model) {
		List<User> users = service.listPatients();
		model.addAttribute("patients", users);
		return "MedicAp/PatientIndex";
	}
	@GetMapping("/patient/{id}")
	public String listatients(@PathVariable int id,Model model) {
		List<MedicalAppointment> appo = medicap.findByPatient_id(id);
		model.addAttribute("medicap", appo);
		return "MedicAp/PatientMedicalIndex";
	}
}
