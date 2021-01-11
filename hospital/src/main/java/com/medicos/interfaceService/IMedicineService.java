package com.medicos.interfaceService;

import java.util.List;
import java.util.Optional;

import com.medicos.model.Medicine;

public interface IMedicineService {
	 public List<Medicine>list();
	 public Optional<Medicine>listId(int id);
	 public int save(Medicine m);
	 public void delete(int id);
}
