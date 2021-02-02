package com.medicos.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicos.interfaceService.IMedicineService;
import com.medicos.model.Medicine;

@RestController
@RequestMapping("api/medicine")
public class MedicineRest {
	@Autowired
	private IMedicineService medicine;
	
	@GetMapping("/list")
	public List<Medicine> list() {
		return medicine.list();
	}
//	Anadir ne headers: content-type application/json
	@PostMapping("/save")
	public void save(@RequestBody Medicine medicina) {
		medicine.save(medicina);
	}
//	No funciona
	@PutMapping("update")
	public void update(@RequestBody Medicine m) {
		medicine.save(m);
	}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		medicine.delete(id);
	}
}