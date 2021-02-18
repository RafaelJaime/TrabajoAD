package com.medicos.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicos.interfaces.IMedicalAppointment;
import com.medicos.model.MedicalAppointment;
import com.medicos.model.User;

@Controller
@RequestMapping("/medicalAppointment")
public class MedicAppointment_controller {
	@Autowired
	private IMedicalAppointment medicap;
	
	@GetMapping("/list")
	public String list(Model model) {
		Collection<MedicalAppointment> appointment = medicap.findByObservations(null);
		System.out.println(appointment);
		model.addAttribute("medicap", appointment);
		return "MedicAp/index";
	}
}
