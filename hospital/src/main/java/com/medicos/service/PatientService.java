package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicos.interfaceService.IPatientService;
import com.medicos.interfaces.IPatient;
import com.medicos.model.Medico;
import com.medicos.model.Patient;

@Service
public class PatientService implements IPatientService{
	
	@Autowired
	private IPatient data;
	
	@Override
	public List<Patient> list() {
		// TODO Auto-generated method stub
		return (List<Patient>) data.findAll();
	}

	@Override
	public Optional<Patient> listId(int id) {
		return data.findById(id);
	}

	@Override
	public int save(Patient m) {
		int res=0;
		Patient patient=data.save(m);
		if(!patient.equals(null))
			res=1;
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

}
