package com.medicos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.medicos.interfaceService.IUserService;
import com.medicos.model.Buy;
import com.medicos.model.Medicine;
import com.medicos.model.User;
import com.medicos.service.BuyService;
import com.medicos.service.MedicineService;
import com.medicos.service.UserService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	
	@Autowired
	private IUserService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	MedicineService medicineService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BuyService buyService;
	
	@Autowired
	HttpSession session;
	
	private com.medicos.model.User user;
	
	
//	@ModelAttribute("cart")
//	public List<Medicine> medicineCart(){
//		@SuppressWarnings("unchecked")
//		List<Integer> content=(List<Integer>) session.getAttribute("cart");
//		return (content == null) ? null : medicineService.findById(234);
//	}
	
//	@ModelAttribute("total_cart")
//	public Double totalCart(){
//		List<Medicine> content=medicineCart();
//		if (content != null)
//			return content.stream().mapToDouble(p -> p.getPrice()).sum();
//		return 0.0;
//	}
	
	@ModelAttribute("my_content")
	public Optional<Buy> myContent(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByName(name);
		return buyService.findById(user.getId());
	}
	
	@GetMapping("/cart/add/{id}")
	public String addCart(Model model, @PathVariable Long id) {
		List<Long>content = (List<Long>) session.getAttribute("cart");
		if (content==null)
				content= new ArrayList<>();
		if(!content.contains(id))
			content.add(id);
		session.setAttribute("cart",content);
		return "redirect:/MedicAp/carro1";
	}
	
	@GetMapping("/cart/delete/{id}")
	public String deleteCart(Model model, @PathVariable Long id) {
		List<Long>content = (List<Long>) session.getAttribute("cart");
		if (content==null)
				return "redirect:/index";
		if(content.isEmpty())
			session.removeAttribute("cart");
		else
			session.setAttribute("cart",content);
		return "redirect:/MedicAp/carro1";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		User patients=service.findByName(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
		model.addAttribute("patients",patients);
		return "Patient/PatientindexEdit";	
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<User> patients=service.findByRole("PATIENT");
		model.addAttribute("patients",patients);
		return "Patient/Patientindex";
	}
	
	@GetMapping("/new")
	public String add(Model model) {
		model.addAttribute("patient",new User());
		return "Patient/PatientformAdd";
	}
	
	@PostMapping("/saveAdd")
	public String saveAdd(User m,Model model, RedirectAttributes flash) {
		m.setRole("PATIENT");
		m.setPassword(encoder.encode(m.getPassword()));
		service.save(m);
		flash.addFlashAttribute("success", "Patient saved successfully.");
		return "redirect:/patient/list";
	}
	
	@PostMapping("/save")
	public String save(User m,Model model, RedirectAttributes flash) {
		m.setRole("PATIENT");
		User oldUser = service.findByName(m.getName());
		System.out.println();
		oldUser.setName(m.getName());
		oldUser.setSurname(m.getSurname());
		oldUser.setAge(m.getAge());
		oldUser.setDirection(m.getDirection());
		oldUser.setFirstname(m.getFirstname());
		service.save(oldUser);
		flash.addFlashAttribute("success", "Patient saved successfully.");
		return "redirect:/patient/list";
	}
	
	@PostMapping("/saveEdit")
	public String saveEdit(User m,Model model, RedirectAttributes flash) {
		m.setRole("PATIENT");
		User oldUser = service.findByName(m.getName());
		oldUser.setName(m.getName());
		oldUser.setSurname(m.getSurname());
		oldUser.setAge(m.getAge());
		oldUser.setDirection(m.getDirection());
		oldUser.setFirstname(m.getFirstname());
		service.save(oldUser);
		flash.addFlashAttribute("success", "Patient saved successfully.");
		return "redirect:/patient/edit";
	}
	
	@GetMapping("/modifyEdit/{id}")
	public String modifyEdit(@PathVariable int id, Model model) {
		Optional <User> patient=service.listId(id);
		model.addAttribute("patient",patient);
		return "Patient/PatientformEdit";
	}
	
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable int id, Model model) {
		Optional <User> patient=service.listId(id);
		model.addAttribute("patient",patient);
		return "Patient/Patientform";
	}
	
	@GetMapping("/password/{id}")
	public String password(@PathVariable int id, Model model) {
		Optional <User> patient=service.listId(id);
		model.addAttribute("patient",patient);
		return "Patient/PatientformPassword";
	}
	
	@PostMapping("/passwordChange")
	public String passwordChange(User m,Model model, RedirectAttributes flash) {
		User oldUser = service.findByName(m.getName());
		oldUser.setPassword(encoder.encode(m.getPassword()));
		service.save(oldUser);
		flash.addFlashAttribute("success", "Patient saved successfully.");
		return "redirect:/patient/edit";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/patient/list";
	}
}
