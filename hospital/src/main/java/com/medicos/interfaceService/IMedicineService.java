package com.medicos.interfaceService;

import java.util.List;
import java.util.Optional;

import com.medicos.model.Medicine;
import com.medicos.model.User;

public interface IMedicineService {
	 public List<Medicine>list();
	 public int save(Medicine m);
	 public void delete(int id);
	 Medicine findByName(String name);
	 public Optional<Medicine> findById(int id);
}
