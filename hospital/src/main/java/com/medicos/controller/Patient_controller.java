package com.medicos.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medicos.interfaceService.IMedicineService;
import com.medicos.interfaceService.IUserService;
import com.medicos.interfaces.IMedicine;
import com.medicos.model.MedicalAppointment;
import com.medicos.model.Medicine;
import com.medicos.model.User;
import com.medicos.service.MedicAppointmentService;

@Controller
@RequestMapping("/patients")
public class Patient_controller {
	
	@Autowired
	private MedicAppointmentService medicap;
	@Autowired
	private IUserService service;
	@Autowired
	private IMedicine mediservice;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping({"/medicalAppointment", "/medicalappointment"})
	public String aks(Model model) {
		return "MedicAp/ask";
	}
	
	@PostMapping({"/medicalAppointment", "/medicalappointment"})
	public String saveAdd(@RequestParam(value="date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model, Principal principal, RedirectAttributes flash) {
		MedicalAppointment cita = new MedicalAppointment();
		cita.setDate(date);
		User usuario = service.findByName(principal.getName());
		cita.setPatient(usuario);
		medicap.save(cita);
		flash.addFlashAttribute("success", "Medical appointment saved successfully.");
		return "redirect:/";
	}
	@GetMapping({"/list", "/list"})
	public String list(Model model, Principal principal) {
		User usuario = service.findByName(principal.getName());
		List<MedicalAppointment> medicap2 = medicap.list(usuario);
		model.addAttribute("medicap", medicap2);
		return "MedicAp/list";
	}
	
	@GetMapping({"/Profile", "/profile"})
	public String profile (Model modelo, Principal principal) {
		User usuario = service.findByName(principal.getName());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("password1", "");
		modelo.addAttribute("password2", "");
		return "MedicAp/profile";
	}
	@PostMapping({"/Profile", "/profile"})
	public String profileSave (User user, Model modelo, Principal principal,RedirectAttributes flash) {
		User usuario = service.findByName(principal.getName());
		if (!user.getFirstname().isEmpty()) {
			usuario.setFirstname(user.getFirstname());
		}
		if (!user.getSurname().isEmpty()) {
			usuario.setSurname(user.getSurname());
		}
		if (user.getAge()>= 18) {
			usuario.setAge(user.getAge());
		}
		if (!user.getDirection().isEmpty()) {
			usuario.setDirection(user.getDirection());
		}
		if (!user.getPassword().isEmpty()) {
			usuario.setPassword(encoder.encode(user.getPassword()));
        }
		service.save(usuario);
		flash.addFlashAttribute("success", "Profile saved successfully.");
		modelo.addAttribute("usuario", usuario);
		return "redirect:/profile";
	}
	
//	Tienda
	
	@GetMapping({"Shop", "shop"})
	public String shop(Model model, HttpServletRequest request) {
		List<Medicine> medicinas = mediservice.findWithStock();
		model.addAttribute("medicines", medicinas);
		return "MedicAp/shop";
	}
	
	@GetMapping({"Carrito", "carrito"})
	public String carrito(Model model, HttpSession sesion) {
		List<Medicine> carrazo = (List<Medicine>) sesion.getAttribute("Lista");
		
		if (carrazo == null) {
			carrazo = new ArrayList<Medicine>();
		}
		model.addAttribute("carro", carrazo);
		return "MedicAp/carro1";
	}
	
	@PostMapping({"add", "Add"})
	public String more(HttpSession sesion, @RequestParam int id) {
		Optional<Medicine> medic = mediservice.findById(id);
		System.out.println(medic.get().toString());
		@SuppressWarnings("unchecked")
		List<Medicine> medicinas = (List<Medicine>) sesion.getAttribute("Lista");
		if (medicinas == null) {
			sesion.setAttribute("Lista", new ArrayList<Medicine>());
		}
		medicinas.add(medic.get());
		sesion.setAttribute("Lista", medicinas);
		return "redirect:/patients/carrito";
	}
	@PostMapping("/destroy")
	public String destroySession(HttpSession sesion) {
		sesion.setAttribute("Lista", new ArrayList<Medicine>());
		return "redirect:/patients/carrito";
	}
	
//	
//	@GetMapping({"Carrito", "carrito"})
//	public String carrito(Model model, HttpSession sesion) {
//		List<Medicine> carrazo = (List<Medicine>) sesion.getAttribute("Lista");
//		
//		if (carrazo == null) {
//			carrazo = new ArrayList<Medicine>();
//		}
//		model.addAttribute("carro", carrazo);
//		return "MedicAp/carro1";
//	}
//	@PostMapping("/destroy")
//	public String destroySession(HttpServletRequest request) {
//		request.getSession().invalidate();
//		return "redirect:/";
//	}
//	@PostMapping({"Agregar/{id}", "agregar/{id}"})
//	public String agregar(@PathVariable int id, HttpServletRequest request) {
//		ArrayList<Medicine> carrito = this.obtenerCarrito(request);
//		Medicine medicina = mediservice.findById(id).stream().findFirst().orElse(null);
//		System.out.println(medicina.getId());
//		for (Medicine medicine : carrito) {
//			if (medicine.getId() == medicina.getId()) {
//				carrito.add(medicina);
//				break;
//			}
//		}
//		this.guardarCarrito(carrito, request);
//		return "redirect:/patients/Shop";
//	}
//	
//	@PostMapping("/quitar/{indice}")
//	public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
//	    ArrayList<Medicine> carrito = this.obtenerCarrito(request);
//	    if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
//	        carrito.remove(indice);
//	        this.guardarCarrito(carrito, request);
//	    }
//	    return "redirect:/Carrito";
//	}
//	
//	
//	
//	
////	Métodos a parte
//	private ArrayList<Medicine> obtenerCarrito(HttpServletRequest request) {
//	    ArrayList<Medicine> carrito = (ArrayList<Medicine>) request.getSession().getAttribute("carrito");
//	    if (carrito == null) {
//	        carrito = new ArrayList<>();
//	    }
//	    System.out.println(carrito);
//	    return carrito;
//	}
//
//	private void guardarCarrito(ArrayList<Medicine> carrito, HttpServletRequest request) {
//	    request.getSession().setAttribute("carrito", carrito);
//	}
}
