package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medicos.interfaceService.IMedicAppoService;
import com.medicos.interfaces.IMedicalAppointment;
import com.medicos.interfaces.IMedicine;
import com.medicos.model.MedicalAppointment;
@Service
public class MedicAppointmentService implements IMedicAppoService {
	@Autowired
	private IMedicalAppointment data;
	
	@Override
	public List<MedicalAppointment> findNoDescription() {
		
		return null;
	}

}
