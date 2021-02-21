package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicos.interfaceService.IMedicineService;
import com.medicos.interfaces.IMedicine;
import com.medicos.model.Medicine;


@Service
public class MedicineService implements IMedicineService{
	@Autowired
	private IMedicine data;
	
	@Override
	public List<Medicine> list() {
		// TODO Auto-generated method stub
		return (List<Medicine>) data.findAll();
	}

	@Override
	public int save(Medicine m) {
		int res=0;
		Medicine medicine=data.save(m);
		if(!medicine.equals(null))
			res=1;
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

	@Override
	public Medicine findByName(String name) {
		return data.findByName(name);
	}

	@Override
	public List<Medicine> findById(int id) {
		return (List<Medicine>) data.findById(id);
	}
}
