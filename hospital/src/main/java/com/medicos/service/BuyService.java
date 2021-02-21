package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicos.interfaces.IBuy;
import com.medicos.interfaces.IMedicine;
import com.medicos.model.Buy;
import com.medicos.model.User;

@Service
public class BuyService {
		
	@Autowired
	IBuy IBuy;
	
	@Autowired
	IMedicine IMedicine;
	
	public Buy Insert(Buy b, User u) {
		b.setPatient_id(u);
		return IBuy.save(b);
	}
	
	public Buy Insert(Buy b) {
		return IBuy.save(b);
	}
	
	public Optional<Buy> findById(int id) {
		return IBuy.findById(id);
	}
	
	public List<Buy> All(Buy b) {
		return IBuy.findAll();
	}
	
}
