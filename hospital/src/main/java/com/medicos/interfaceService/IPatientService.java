package com.medicos.interfaceService;

import java.util.List;
import java.util.Optional;

import com.medicos.model.Patient;

public interface IPatientService {
	 public List<Patient>list();
	 public Optional<Patient>listId(int id);
	 public int save(Patient p);
	 public void delete(int id);
}
